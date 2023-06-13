package br.com.todoApp.todoApp.dtos;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class TaskDto {

    @NotBlank
    private String description;

    private boolean done = false;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
