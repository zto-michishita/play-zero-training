package controllers;

import play.data.validation.Constraints;

public class DeleteFormData {
    @Constraints.Required
    private Long id;

    public DeleteFormData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
