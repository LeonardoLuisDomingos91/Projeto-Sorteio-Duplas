package com.invillia.amigoSecreto.domain;

import javax.persistence.*;

@Entity
public class DoublesFriends {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name1;

    @Column(nullable = false)
    private String name2;

    public DoublesFriends() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String friend1) {
        this.name1 = friend1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String friend2) {
        this.name2 = friend2;
    }
}
