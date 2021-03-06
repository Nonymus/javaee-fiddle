package de.nonymus.testing.foobar.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Device {

    @Id
    @Getter
    @Setter
    private UUID id = UUID.randomUUID();

    @Getter
    @Setter
    @OneToMany(mappedBy = "device", cascade = { CascadeType.REMOVE })
    // Also remove variable values when deleting a device
    private List<VariableValue> variableValues;

    @Getter
    @Setter
    private String name;

}
