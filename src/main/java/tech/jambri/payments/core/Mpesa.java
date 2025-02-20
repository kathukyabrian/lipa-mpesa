package tech.jambri.payments.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mpesa {
    private static final Logger logger = LogManager.getLogger(Mpesa.class);

    public static void requestPayment() {
        String accessToken = Auth.getAccessToken(logger);
    }
}
