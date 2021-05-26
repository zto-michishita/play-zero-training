package test.controller;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.api.test.*;
import play.test.WithApplication;
import com.google.common.collect.ImmutableMap;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.GET;
import static play.test.Helpers.POST;
import static play.test.Helpers.BAD_REQUEST;
import static play.test.Helpers.route;
import static play.test.Helpers.*;

public class FormControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void 掲示板が表示出来るかどうか() {
        Http.RequestBuilder request = Helpers.fakeRequest()
            .method(GET)
            .uri("/board");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void fixページが表示できるかどうか() {
        Http.RequestBuilder request = Helpers.fakeRequest()
            .method(GET)
            .bodyForm(ImmutableMap.of("id",""))
            .uri("/board/fix?id=1");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void 投稿が出来るかどうか() {
        Http.RequestBuilder request = Helpers.fakeRequest()
            .method(POST)
            .bodyForm(ImmutableMap.of("name","投稿するよ", "text", "投稿"))
            .uri("/board/create");

        Result result = route(app, request);
        assertEquals(SEE_OTHER, result.status());
    }

    public void 投稿のFormにnullがあったら400番が返るかどうか() {
        Http.RequestBuilder request = Helpers.fakeRequest()
            .method(POST)
            .bodyForm(ImmutableMap.of("name","投稿するよ", "text", ""))
            .uri("/board/create");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
    }

    @Test
    public void 投稿が削除出来るかどうか() {
        Http.RequestBuilder request = Helpers.fakeRequest()
            .method(POST)
            .bodyForm(ImmutableMap.of("id","1"))
            .uri("/board/delete");
    public void 投稿のFormにJSインジェクションがあったらただの文字列になるかどうか() {
        Http.RequestBuilder request = new Http.RequestBuilder()
            .method(POST)
            .bodyForm(ImmutableMap.of("name","投稿するよ", "text", "<script> 10000 * 2 <script>"))
            .uri("/board/create");

        Result result = route(app, request);
        assertThat(contentAsString(result), is(containsString("")));
    }
            .bodyForm(ImmutableMap.of("id",""))
            .uri("/board/delete");

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
    }

    @Test
    public void 投稿の編集が出来るかどうか() {
        Http.RequestBuilder request = Helpers.fakeRequest()
            .method(POST)
            .bodyForm(ImmutableMap.of("name","編集するよ", "text", "編集"))
            .uri("/board/update");

        Result result = route(app, request);
        assertEquals(SEE_OTHER, result.status());
    }

    @Test
    public void 投稿の編集Formにnullがあったら400番が返るかどうか() {
        Http.RequestBuilder textNullRequest = Helpers.fakeRequest()
            .method(POST)
            .bodyForm(ImmutableMap.of("name","テキストがnull", "text", ""))
            .uri("/board/create");

        textNullRequest = CSRFTokenHelper.addCSRFToken(textNullRequest);
        Result textNullResult = route(app, textNullRequest);
        assertEquals(BAD_REQUEST, textNullResult.status());


        Http.RequestBuilder nameNullRequest = Helpers.fakeRequest()
            .method(POST)
            .bodyForm(ImmutableMap.of("name","", "text", "nameがnull"))
            .uri("/board/create");

        nameNullRequest = CSRFTokenHelper.addCSRFToken(nameNullRequest);
        Result nameNullResult = route(app, nameNullRequest);
        assertEquals(BAD_REQUEST, nameNullResult.status());
    }

}
