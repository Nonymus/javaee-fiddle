package de.nonymus.testing.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Parent {

    @Id
    @Getter
    private UUID id = UUID.randomUUID();

    @Getter
    @OneToMany(mappedBy = "owner")
    @MapKeyColumn(name = "key")
    private Map<String, KeyValue> params = new HashMap<String, KeyValue>();

    @Getter
    @Setter
    private String handle;

    public void setName(String name) {
        set("name", name);
    }

    public String getName() {
        return get("name");
    }

    private void set(String field, String value) {
        KeyValue existing = this.getParams().get(field);
        if (existing != null) {
            existing.setValue(value);
        } else {
            existing = new KeyValue(this);
            existing.setKey(field);
            existing.setValue(value);
            this.getParams().put(field, existing);
        }
    }

    private String get(String field) {
        KeyValue existing = this.getParams().get(field);
        if (existing != null) {
            return existing.getValue();
        } else {
            return null;
        }
    }

}
