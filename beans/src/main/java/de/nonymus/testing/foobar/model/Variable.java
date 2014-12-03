package de.nonymus.testing.foobar.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQueries({ @NamedQuery(name = "variable.findByName", query = "SELECT v FROM Variable v WHERE v.name = :name") })
public class Variable {

    @Id
    @Getter
    @Setter
    private UUID id = UUID.randomUUID();

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "variable", cascade = { CascadeType.REMOVE })
    // Also delete all values in devices for this variable
    private List<VariableValue> values;
}
