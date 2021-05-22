package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import com.google.common.collect.ImmutableMap;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.GET;
import static play.test.Helpers.POST;
import static play.test.Helpers.route;

public class FormControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void fixページが表示できるかどうか() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/board/fix?id=1");

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
    public void 投稿が出来るかどうか() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyForm(ImmutableMap.of("name","aaaa", "text", "adadawdada"))
                .uri("/board/create");

        Result result = route(app, request);
        assertEquals(SEE_OTHER, result.status());
    }

    @Test
    public void 投稿が削除出来るかどうか() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyForm(ImmutableMap.of("id","1"))
                .uri("/board/delete");

        Result result = route(app, request);
        assertEquals(SEE_OTHER, result.status());
    }

}
