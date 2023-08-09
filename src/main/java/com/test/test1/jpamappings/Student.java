package com.test.test1.jpamappings;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
@Entity
//@ToString
@Setter
@Getter
public class Student {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToOne(mappedBy = "student" ,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Laptop laptop;
}
