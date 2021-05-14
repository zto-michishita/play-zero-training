package controllers;

import models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.*;

import io.ebean.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import com.google.common.collect.Lists;
import java.util.ArrayList;

import static play.libs.Scala.asScala;
    
@Singleton
public class FormController extends Controller {
    private final Form<WidgetData> form;
    private final Form<DeleteFormData> deleteForm;

    private MessagesApi messagesApi;
    private List<User> users;

    private final Long master = 0l;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public FormController(FormFactory formFactory, MessagesApi messagesApi) {
        this.form = formFactory.form(WidgetData.class);
        this.deleteForm = formFactory.form(DeleteFormData.class);
        this.messagesApi = messagesApi;
    }

    public Result showForm(Http.Request request) {
        this.users = User.finder.all();
        return ok(views.html.board.render(users, deleteForm, form, request, messagesApi.preferred(request)));
    }

    public Result create(Http.Request request) {
        final Form<WidgetData> boundForm = form.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.board.render(users, deleteForm, boundForm, request, messagesApi.preferred(request)));
        } else {
            WidgetData data = boundForm.get();
            User addUser = new User();
            addUser.setName(data.getName());
            addUser.setText(data.getText());
            Ebean.save(addUser);
            return redirect(routes.FormController.showForm()).flashing("info", "書き込みました");
        }
    }

    public Result delete(Http.Request request) {
        final Form<DeleteFormData> boundForm = deleteForm.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.board.render(users, boundForm, form, request, messagesApi.preferred(request)));
        } else {
            DeleteFormData data = boundForm.get();
            User.finder.ref(data.getId()).delete();
            return redirect(routes.FormController.showForm()).flashing("info", "削除しました");
        }
    }

}
