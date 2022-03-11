package com.jpabook.jpastudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Member2 {

    @Id
    private Long id;

    private String name;

    protected Member2() {}
}
