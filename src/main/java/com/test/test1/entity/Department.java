package com.test.test1.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class Department implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Department can't be null")
    private String departmentName;
    @NotBlank(message = "Address can't be blank")
    private String departmentAddress;
    @NotBlank(message = "Department code can't be null")
    private String departmentCode;
}