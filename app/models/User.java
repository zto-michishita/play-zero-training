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
    public Long id;
    
    @Constraints.Required
    public String name;

    @Constraints.Required
    public String text;


}
