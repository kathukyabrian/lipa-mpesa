package tech.jambri.payments.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.jambri.payments.config.ApplicationProperties;
import tech.jambri.payments.core.factory.ServiceRepositoryFactory;
import tech.jambri.payments.dto.STKErrorResponse;
import tech.jambri.payments.dto.STKRequest;
import tech.jambri.payments.dto.STKSuccessResponse;
import tech.jambri.payments.util.DarajaUtil;
import tech.jambri.payments.util.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Mpesa {
    private static final Logger logger = LogManager.getLogger(Mpesa.class);

    public static void requestPayment(Integer amount, String phoneNumber, String accountRef, String transactionDesc) {
        ObjectMapper objectMapper = new ObjectMapper();

        String accessToken = Auth.getAccessToken(logger);

        ApplicationProperties applicationProperties = ServiceRepositoryFactory.getApplicationProperties();
        STKRequest stkRequest = new STKRequest();
        stkRequest.setTimeStamp(DarajaUtil.generateTimestamp());
        stkRequest.setAmount(amount);
        try {
            stkRequest.setPartyA(Long.parseLong(phoneNumber));
            stkRequest.setPhoneNumber(Long.parseLong(phoneNumber));
            stkRequest.setBusinessShortCode(Long.parseLong(applicationProperties.getShortCode()));
            stkRequest.setPartyB(Long.parseLong(applicationProperties.getIdentifier()));
        } catch (NumberFormatException ignore) {
        }
        stkRequest.setTransactionType(applicationProperties.getTransactionType());
        stkRequest.setCallBackUrl(applicationProperties.getCallbackUrl());
        stkRequest.setAccountReference(accountRef);
        stkRequest.setTransactionDesc(transactionDesc);

        String password = DarajaUtil.generatePassword(applicationProperties.getShortCode(), applicationProperties.getPassKey(), stkRequest.getTimeStamp());
        stkRequest.setPassword(password);

        String url = applicationProperties.getPaymentUrl();

        String body = null;
        try {
            body = objectMapper.writeValueAsString(stkRequest);
        } catch (com.fasterxml.jackson.core.JsonProcessingException ignore) {
        }

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Bearer " + accessToken);

        String response = null;
        try {
            response = HttpUtil.post(url, body, headerMap, MediaType.get("application/json; charset=utf-8"));
        } catch (IOException e) {
            logger.error("Encountered exception ", e);
        }

        logger.info("system|got response from daraja: {}", response);

        STKSuccessResponse successResponse = null;
        STKErrorResponse errorResponse = null;
        try {
            successResponse = objectMapper.readValue(response, STKSuccessResponse.class);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            try {
                errorResponse = objectMapper.readValue(response, STKErrorResponse.class);
            } catch (JsonProcessingException ignore) {
            }
        }


        if (successResponse != null) {
            logger.info("success:::::::{}", successResponse);
        }

        if (errorResponse != null) {
            logger.info("error:::::::::::{}", errorResponse);
        }

    }
}
