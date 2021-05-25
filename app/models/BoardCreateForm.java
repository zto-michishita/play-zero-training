package models;

import play.data.validation.Constraints;

public class BoardCreateForm {

    @Constraints.Required(message="必須入力です")
    @Constraints.MaxLength(value=255, message="255文字以下にしてください。")
    private String name;

    @Constraints.Required(message="必須入力です")
    @Constraints.MaxLength(value=255, message="255文字以下にしてください。")
    private String text;

    public BoardCreateForm() {
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