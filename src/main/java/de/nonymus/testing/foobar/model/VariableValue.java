package de.nonymus.testing.foobar.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class VariableValue {

    @Id
    @Getter
    @Setter
    private UUID id = UUID.randomUUID();

    @Getter
    @Setter
    @ManyToOne
    private Device device;

    @Getter
    @Setter
    @ManyToOne
    private Variable variable;

    @Getter
    @Setter
    private String value;
}
