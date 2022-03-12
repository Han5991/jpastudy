package com.jpabook.jpastudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MBR")
@Data
@AllArgsConstructor
public class Member2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String username;

//    private Integer age;
//
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    @Lob //큰 타입
//    private String description;

    public Member2() {}
}
