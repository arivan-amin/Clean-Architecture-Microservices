package io.github.arivanamin.cam.backend.sso.application.secret;

import io.github.arivanamin.cam.backend.sso.core.secret.ClientSecretGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Base64;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class SecureRandomSecretGenerator implements ClientSecretGenerator {

    public static final int ENCODING_BYTES = 32;

    public static SecureRandomSecretGenerator newInstance () {
        return new SecureRandomSecretGenerator();
    }

    @Override
    public String generateSecret () {
        byte[] bytes = generateSecureRandomSecret();
        return encodeSecretToBase64(bytes);
    }

    private static byte[] generateSecureRandomSecret () {
        byte[] bytes = new byte[ENCODING_BYTES];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }

    private static String encodeSecretToBase64 (byte[] bytes) {
        return Base64.getUrlEncoder()
            .withoutPadding()
            .encodeToString(bytes);
    }
}
