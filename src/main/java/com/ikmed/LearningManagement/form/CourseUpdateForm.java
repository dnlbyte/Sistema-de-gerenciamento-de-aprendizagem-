package com.ikmed.LearningManagement.form;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateForm {
    @NotNull(message = "The name field cannot be empty")
    @NotBlank(message = "The name field cannot be blank.")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters.")
    private String name;

    @NotNull(message = "The description field cannot be empty")
    @NotBlank(message = "The description field cannot be blank.")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters.")
    private String description;

    @NotNull(message = "The price field cannot be empty")
    @DecimalMax(value = "99999.99")
    @Digits(integer = 16, fraction = 2)
    @DecimalMin(value = "0.00")
    private Double price;

    @NotNull(message = "The price field cannot be empty")
    @DecimalMax(value = "99999.99")
    @Digits(integer = 16, fraction = 2)
    @DecimalMin(value = "0.01")
    private Double discount;

    @NotNull(message = "The duration field cannot be empty")
    @Min(value = 1)
    @Max(value = 9999)
    private Integer duration;

    @NotNull(message = "The startAt field cannot be empty")
    private LocalDate startAt;

    @NotNull(message = "The finishAt field cannot be empty")
    private LocalDate finishAt; //
}
