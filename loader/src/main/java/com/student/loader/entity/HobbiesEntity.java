package com.student.loader.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "student_hobbies")
public class HobbiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "hobby1")
    private String hobby1;

    @Column(name = "hobby2")
    private String hobby2;

    @Column(name = "hobby3")
    private String hobby3;

}


