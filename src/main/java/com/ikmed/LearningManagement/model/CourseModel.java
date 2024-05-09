package com.ikmed.LearningManagement.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name="TB_COURSE")
public class CourseModel {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "price", length = 10, nullable = false)
    private Double price;

    @Column(name = "discount", length = 10, nullable = false)
    private Double discount;

    @Column(name = "duration", length = 10, nullable = false)
    private Integer dutarion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_at")
    private LocalDate startAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_at")
    private LocalDate finishAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @Version
    @Column(name = "version", nullable = false)
    private long version;

}
