package org.launchcode.uTrain.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    public int getId() { return id; }
}
