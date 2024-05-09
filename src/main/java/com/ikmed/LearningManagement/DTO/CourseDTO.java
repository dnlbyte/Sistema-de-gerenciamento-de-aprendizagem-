package com.ikmed.LearningManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String name;
    private String description;
    private Double price;
    private Double discount;
    private Integer dutarion;
    private Date startAt;
    private Date finishAt;
}
