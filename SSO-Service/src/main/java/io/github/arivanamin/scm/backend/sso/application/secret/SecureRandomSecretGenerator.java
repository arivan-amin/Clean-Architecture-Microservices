package io.github.arivanamin.scm.backend.sso.application.secret;

import io.github.arivanamin.scm.backend.sso.core.secret.ClientSecretGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Base64;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class SecureRandomSecretGenerator implements ClientSecretGenerator {

    public static final int ENCODING_BYTES = 32;
    private final SecureRandom secureRandom = new SecureRandom();

    public static SecureRandomSecretGenerator newInstance () {
        return new SecureRandomSecretGenerator();
    }

    @Override
    public String generateSecret () {
        byte[] bytes = generateSecureRandomSecret();
        return encodeSecretToBase64(bytes);
    }

    private byte[] generateSecureRandomSecret () {
        byte[] bytes = new byte[ENCODING_BYTES];
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    private String encodeSecretToBase64 (byte[] bytes) {
        return Base64.getUrlEncoder()
            .withoutPadding()
            .encodeToString(bytes);
    }
}
