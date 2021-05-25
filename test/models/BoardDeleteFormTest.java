package test.models;

import models.BoardDeleteForm;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.api.test.*;
import com.google.common.collect.ImmutableMap;

import static org.junit.matchers.JUnitMatchers.containsString;
import static play.test.Helpers.*;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class BoardDeleteFormTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void モデルが生成出来るかどうか() {
        BoardDeleteForm form = new BoardDeleteForm();
        form.setId(1L);

        assertThat(form.getId(), equalTo(1L));
    }

    @Test
    public void 投稿の編集Formにnullがあったらエラーメッセージが返るかどうか() {
        String errorText = "必須入力です";

        try {
            Http.RequestBuilder request = Helpers.fakeRequest()
                .method(POST)
                .bodyForm(ImmutableMap.of("name","テキストがnull", "text", ""))
                .uri("/board/create");

            request = CSRFTokenHelper.addCSRFToken(request);
            Result result = route(app, request);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(),containsString(errorText));
        }
    }
}