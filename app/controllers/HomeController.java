package controllers;

import play.mvc.*;

import javax.inject.Singleton;
import java.util.List;

import models.User;

public class HomeController extends Controller {

    public Result index() {
        return ok(views.html.index.render());
    }

    public Result hello(String massage) {
        return ok(views.html.hello.render(massage));
    }

}
