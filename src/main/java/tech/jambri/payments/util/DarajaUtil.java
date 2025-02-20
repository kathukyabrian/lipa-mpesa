package tech.jambri.payments.util;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class DarajaUtil {


    public static String generateTimestamp(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }

    public static String generatePassword(String shortCode, String passKey, String timestamp){
        String rawPassword = shortCode + passKey + timestamp;
        String encodedPassword = Base64.getEncoder().encodeToString(rawPassword.getBytes(StandardCharsets.UTF_8));
        return encodedPassword;
    }

}
