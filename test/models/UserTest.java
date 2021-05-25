package test.models;

import models.User;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import play.db.*;
import play.db.evolutions.Evolutions;

import static org.junit.Assert.assertEquals;

public class UserTest extends WithApplication {

    Database database = Databases.inMemory();

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    public UserTest() {
        Evolutions.applyEvolutions(database);
    }

    @Test
    public void モデルが生成出来るかどうか() {
        User user = new User();
        user.setName("test");
        user.setText("test");

        assertEquals("test", user.getName());
        assertEquals("test", user.getText());
    }

    @Test
    public void データベースから値を取ってこれるか() {
        User user = User.finder.byId(1L);
        
        assertEquals("名無し", user.getName());
        assertEquals("サンプルテキストです。", user.getText());
    }
}
