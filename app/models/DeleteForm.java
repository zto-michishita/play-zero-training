package models;

import play.data.validation.Constraints;

public class DeleteForm {
    @Constraints.Required
    private Long id;

    public DeleteForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
