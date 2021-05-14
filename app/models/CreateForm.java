package models;

import play.data.validation.Constraints;

public class CreateForm {

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String text;

    public CreateForm() {
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