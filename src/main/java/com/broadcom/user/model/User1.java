package com.broadcom.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User1 {
    @Id
    private Long id;
    private String name;
    private int age;
    private String address1;
    private String address2;
}
