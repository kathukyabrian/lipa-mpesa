package tech.jambri.payments.util;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

class DarajaUtilTest {

    @Test
    void testGenerateTimestamp() {
        String timestamp = DarajaUtil.generateTimestamp();

        assert timestamp.length() == 14;
    }

    @Test
    void testGeneratePassword() {
        String shortCode = "12345";
        String passKey = "testpasskey";
        String timestamp = "20250812212312";

        String password = DarajaUtil.generatePassword(shortCode, passKey, timestamp);
        String decrypted = new String(Base64.getDecoder().decode(password.getBytes()), StandardCharsets.UTF_8);
        assert decrypted.equals("12345testpasskey20250812212312");

    }

    @Test
    void testGenerateAccessToken() {
        String consumerKey = "bbb";
        String consumerSecret = "aaa";

        String accessToken = DarajaUtil.generateAccessToken(consumerKey, consumerSecret);
        String decrypted = new String(Base64.getDecoder().decode(accessToken.getBytes()), StandardCharsets.UTF_8);

        assert decrypted.equals("bbb:aaa");
    }
}