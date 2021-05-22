package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void トップが表示出来るかどうか() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void 掲示板が表示出来るかどうか() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/board");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void fixページが表示できるかどうか() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/board/fix?id=1");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}
