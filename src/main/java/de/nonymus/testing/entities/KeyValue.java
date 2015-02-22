package de.nonymus.testing.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Entity
@IdClass(CompositeId.class)
public class KeyValue {

    @Id
    @Type(type="pg-uuid")
    private UUID id = UUID.randomUUID();

    @Getter
    @Setter
    @Id
    private String key;

    @Getter
    @Setter
    private String value;

}