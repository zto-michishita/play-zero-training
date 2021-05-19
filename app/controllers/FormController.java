package controllers;

import models.User;
import models.BoardCreateForm;
import models.BoardDeleteForm;
import models.BoardUpdateForm;

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
    private final Form<BoardCreateForm> boardCreateForm;
    private final Form<BoardDeleteForm> boardDeleteForm;
    private final Form<BoardUpdateForm> boardUpdateForm;

    private MessagesApi messagesApi;
    private List<User> users;
    private User updateUser;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public FormController(FormFactory formFactory, MessagesApi messagesApi) {
        this.users = User.finder.all();
        this.messagesApi = messagesApi;

        this.boardCreateForm = formFactory.form(BoardCreateForm.class);
        this.boardDeleteForm = formFactory.form(BoardDeleteForm.class);
        this.boardUpdateForm = formFactory.form(BoardUpdateForm.class);
    }

    public Result showForm(Http.Request request) {
        this.users = User.finder.all();
        return ok(views.html.board.render(users, boardDeleteForm, boardCreateForm, request, messagesApi.preferred(request)));
    }

    public Result fixForm(Http.Request request, Long id) {
        if(id != null && User.finder.byId(id) != null) {
            this.updateUser = User.finder.byId(id);
            BoardUpdateForm form = new BoardUpdateForm();
            form.setName(this.updateUser.getName());
            form.setText(this.updateUser.getText());
            return ok(views.html.fix.render(updateUser, boardUpdateForm.fill(form), request, messagesApi.preferred(request)));
        }
        else {
            return redirect(routes.FormController.showForm());
        }
    }

    public Result create(Http.Request request) {
        final Form<BoardCreateForm> boundForm = boardCreateForm.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.board.render(users, boardDeleteForm, boundForm, request, messagesApi.preferred(request)));
        } else {
            BoardCreateForm data = boundForm.get();
            User addUser = new User();
            addUser.setName(data.getName());
            addUser.setText(data.getText());
            Ebean.save(addUser);
            return redirect(routes.FormController.showForm()).flashing("info", "書き込みました");
        }
    }

    public Result delete(Http.Request request) {
        final Form<BoardDeleteForm> boundForm = boardDeleteForm.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.board.render(users, boundForm, boardCreateForm, request, messagesApi.preferred(request)));
        } else {
            BoardDeleteForm data = boundForm.get();
            User.finder.ref(data.getId()).delete();
            return redirect(routes.FormController.showForm()).flashing("info", "削除しました");
        }
    }

    public Result update(Http.Request request) {
        final Form<BoardUpdateForm> boundForm = boardUpdateForm.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.fix.render(updateUser, boundForm, request, messagesApi.preferred(request)));
        } else {
            BoardUpdateForm data = boundForm.get();
            this.updateUser.setName(data.getName());
            this.updateUser.setText(data.getText());
            Ebean.save(this.updateUser);
            return redirect(routes.FormController.showForm()).flashing("info", "修正しました");
        }
    }

}