package de.nonymus.testing.foobar.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Security {

    public static final int SALT_BYTES = 8;
    public static final int ITERATIONS = 1024;

    public static byte[] hashPassword(final String password, final byte[] salt) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        byte[] pwHash = digest.digest(password.getBytes("UTF-8"));
        for (int i = 0; i < ITERATIONS; i++) {
            pwHash = digest.digest(pwHash);
        }
        return pwHash;
    }

    public static byte[] randomSalt() throws NoSuchAlgorithmException {
        SecureRandom rng = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[SALT_BYTES];
        rng.nextBytes(salt);
        return salt;
    }

    public static boolean authenticate(String password, byte[] pwHash, byte[] salt) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        if (password == null) {
            // When no password given, compute for empty password to harden
            // against timing attack
            password = "";
        }
        byte[] proposedHash = hashPassword(password, salt);
        return Arrays.equals(proposedHash, pwHash);
    }
}