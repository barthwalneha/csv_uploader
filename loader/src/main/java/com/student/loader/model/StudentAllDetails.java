package com.student.loader.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.opencsv.bean.CsvBindByPosition;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class StudentAllDetails {

    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private Integer age;

    @CsvBindByPosition(position = 2)
    private Integer classId;

    @CsvBindByPosition(position = 3)
    private String street;

    @CsvBindByPosition(position = 4)
    private String city;

    @CsvBindByPosition(position = 5)
    private String state;

    @CsvBindByPosition(position = 6)
    private Integer zipcode;

    @CsvBindByPosition(position = 7)
    private String hobby1;

    @CsvBindByPosition(position = 8)
    private String hobby2;

    @CsvBindByPosition(position = 9)
    private String hobby3;

    private Integer id;
    private Integer hobbyId;
    private Integer addressId;


}
