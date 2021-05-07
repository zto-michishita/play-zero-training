package models;

import io.ebean.Model;

import play.data.validation.Constraints;

import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Users extends Model {
    @Id
    public Integer id;
    
    @Constraints.Required
    public String name;

    @Constraints.Required
    public String text;
}
