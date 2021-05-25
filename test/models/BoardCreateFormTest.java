package test.models;

import models.BoardCreateForm;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import com.google.common.collect.ImmutableMap;
import play.api.test.*;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.matchers.JUnitMatchers.containsString;
import static play.test.Helpers.*;

public class BoardCreateFormTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void モデルが生成出来るかどうか() {
        BoardCreateForm form = new BoardCreateForm();
        form.setName("test");
        form.setText("test");

        assertEquals("test", form.getName());
        assertEquals("test", form.getText());
    }

    @Test
    public void バリデーションが効いてるかどうか() {
        String errorText = "255文字以下にしてください。";
        String maxValue256 = "oBb0VqZBiMAaK8jDVxZULhATER8jbsVJGgJ3TjWqIRHEIU5IvR1St6eKltyL7kpKbf5oVtcj7QobKxWZN0D8nw6IMBNWIlPuPz0STqkpj9fiFdXxA7D2y3QaCX7ZveWTyTNLoHDoA4Ios73F6zbnu9WaR2Js8TOLmmBsvhDaWetk8SwqW0EidGVPrOLZlgSxtyYU5Y3uhjUrBL9jzCIyHKCJWNeldy8CEu403E9ryIPNKEpE6UAPY6RN5qcRxsta";

        try {
            Http.RequestBuilder request = Helpers.fakeRequest()
                .method(POST)
                .bodyForm(ImmutableMap.of("name", maxValue256 , "text", "投稿"))
                .uri("/board/create");

            request = CSRFTokenHelper.addCSRFToken(request);

            Result result = route(app, request);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(),containsString(errorText));
        }

        try {
            Http.RequestBuilder request = Helpers.fakeRequest()
                .method(POST)
                .bodyForm(ImmutableMap.of("name", "test", "text", maxValue256))
                .uri("/board/create");

            request = CSRFTokenHelper.addCSRFToken(request);

            Result result = route(app, request);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(),containsString(errorText));
        }
    }

    @Test
    public void 投稿の編集Formにnullがあったらエラーメッセージが返るかどうか() {
        String errorText = "必須入力です";

        try {
            Http.RequestBuilder textNullRequest = Helpers.fakeRequest()
                .method(POST)
                .bodyForm(ImmutableMap.of("name","テキストがnull", "text", ""))
                .uri("/board/create");

            textNullRequest = CSRFTokenHelper.addCSRFToken(textNullRequest);
            Result textNullResult = route(app, textNullRequest);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(),containsString(errorText));
        }

        try {
            Http.RequestBuilder nameNullRequest = Helpers.fakeRequest()
                .method(POST)
                .bodyForm(ImmutableMap.of("name","", "text", "nameがnull"))
                .uri("/board/create");

            nameNullRequest = CSRFTokenHelper.addCSRFToken(nameNullRequest);
            Result nameNullResult = route(app, nameNullRequest);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(),containsString(errorText));
        }
    }


}