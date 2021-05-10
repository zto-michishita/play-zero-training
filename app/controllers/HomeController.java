package controllers;

import play.mvc.*;

import java.util.List;

import models.User;

public class HomeController extends Controller {
    public Result index() {
        return ok(views.html.index.render());
    }

    public Result hello(String massage) {
        return ok(views.html.hello.render(massage));
    }

    public Result board() {        
        List<User> users = User.finder.all();
        return ok(views.html.board.render(users));
    }
}
