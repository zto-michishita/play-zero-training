package models;

import io.ebean.Model;
import io.ebean.Finder;

import play.data.validation.Constraints;

import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class User extends Model {
    public static Finder<Long,User> finder = new Finder<>(User.class);

    @Id
    private Long id;
    
    @Constraints.Required
    private String name;

    @Constraints.Required
    private String text;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
