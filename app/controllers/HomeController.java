package controllers;

import play.mvc.*;

import io.ebean.*;
import java.util.List;

import models.Users;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public Result hello(String massage) {
        return ok(views.html.hello.render(massage));
    }

    public Result database() {
        List<Users> user = Ebean.find(Users.class).findList();

        return ok(views.html.database.render(user));
    }
}
