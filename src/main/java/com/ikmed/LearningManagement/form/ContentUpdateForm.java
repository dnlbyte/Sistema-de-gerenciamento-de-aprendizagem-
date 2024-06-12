package com.ikmed.LearningManagement.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentUpdateForm {
    @NotNull(message = "The title field cannot be empty")
    @NotBlank(message = "The title field cannot be blank.")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters.")
    private String title;
}
