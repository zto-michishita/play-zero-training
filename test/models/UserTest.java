package test.models;

import models.User;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import play.db.*;
import play.db.evolutions.Evolutions;
import io.ebean.*;

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
    public void データベースから値を取れるかどうか() {
        User user = User.finder.byId(1L);
        
        assertEquals("名無し", user.getName());
        assertEquals("サンプルテキストです。", user.getText());
    }

    @Test
    public void sqlが含まれていたらエスケープされるかどうか() {
        User user = new User();
        user.setName("攻撃するよ");
        user.setText("'or 1 = 1;drop table users");

        Ebean.save(user);

        User findUser = User.finder.byId(1L);
        
        assertEquals("名無し", findUser.getName());
        assertEquals("サンプルテキストです。", findUser.getText());

        User atackUser = User.finder.byId(3L);
        assertEquals("攻撃するよ", atackUser.getName());
    }
}
