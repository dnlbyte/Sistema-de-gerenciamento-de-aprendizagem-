package com.ikmed.LearningManagement.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleForm {
    @NotNull(message = "The title field cannot be empty")
    @NotBlank(message = "The title field cannot be blank.")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters.")
    private String title;
}
