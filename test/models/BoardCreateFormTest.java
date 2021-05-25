package test.models;

import models.BoardCreateForm;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;

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
}