package models;

import play.data.validation.Constraints;

public class BoardDeleteForm {
    @Constraints.Required(message="必須入力です")
    private Long id;

    public BoardDeleteForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
