package com.test.test1.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;
    private String firstname;
    private String lastname;
    private String email;
//    private String email2;
    private String profession;

}

