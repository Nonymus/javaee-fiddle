package de.nonymus.testing.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(CompositeId.class)
public class KeyValue {

    public KeyValue() {
    }

    public KeyValue(Parent parent) {
        this.owner = parent;
    }

    @Getter
    @Setter
    @Id
    @ManyToOne
    private Parent owner;

    @Getter
    @Setter
    @Id
    private String key;

    @Getter
    @Setter
    private String value;

}