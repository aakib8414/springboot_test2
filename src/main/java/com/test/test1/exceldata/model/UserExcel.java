package com.test.test1.exceldata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_excel_data")
public class UserExcel {

    @Id
    private long id;
    private String firstname;
    private String lastname;
    private String email;
//    private String email2;
    private String profession;

}

