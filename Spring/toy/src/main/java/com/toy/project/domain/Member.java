package com.toy.project.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
