package br.com.it.helpdesk.infra;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class Pbkdf2PasswordEncoder {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    public String encode(CharSequence rawPassword) {
        try {
            byte[] salt = getSalt();
            byte[] hash = hashPassword(rawPassword.toString(), salt);
            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            String[] parts = encodedPassword.split(":");
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] hash = Base64.getDecoder().decode(parts[1]);
            byte[] testHash = hashPassword(rawPassword.toString(), salt);
            return java.util.Arrays.equals(hash, testHash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getSalt() {
        return "randomSalt".getBytes(); // Apenas para fins de exemplo. Substitua por uma geração de salt segura.
    }

    private byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        return factory.generateSecret(spec).getEncoded();
    }
}
