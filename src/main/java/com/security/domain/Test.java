package com.security.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Test {

    @Id
    private String testId;

    private String name;
}
