package com.springbootjwt.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @Column
    private Long id;

    private String name;
}