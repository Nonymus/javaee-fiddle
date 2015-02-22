package de.nonymus.testing.entities;

import java.io.Serializable;
import java.util.UUID;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CompositeId implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    UUID id;
    String key;
}
