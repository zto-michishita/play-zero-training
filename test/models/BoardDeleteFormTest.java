package test.models;

import models.BoardDeleteForm;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

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
}