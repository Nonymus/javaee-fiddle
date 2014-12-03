package de.nonymus.testing.foobar.model;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import de.nonymus.testing.foobar.util.Security;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="applicationusers")
public class User {

    @Id
    @Getter
    @Setter
    private UUID id = UUID.randomUUID();

    @Getter
    @Setter
    @NotNull
    private String login;
    
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NotNull
    private byte[] passwordHash;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NotNull
    private byte[] salt;

    public void setPassword(String newpassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] salt = Security.randomSalt();
        this.setSalt(salt);
        this.setPasswordHash(Security.hashPassword(newpassword, salt));
    }

    public boolean authenticate(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return Security.authenticate(password, this.getPasswordHash(), this.getSalt());
    }
}
