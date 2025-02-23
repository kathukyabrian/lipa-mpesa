package io.github.kathukyabrian.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kathukyabrian.dto.*;
import okhttp3.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.github.kathukyabrian.config.ApplicationProperties;
import io.github.kathukyabrian.constants.ServiceConstants;
import io.github.kathukyabrian.core.factory.ServiceRepositoryFactory;
import io.github.kathukyabrian.dto.result.CallBackItem;
import io.github.kathukyabrian.dto.result.DarajaSTKCallBack;
import io.github.kathukyabrian.dto.result.MpesaResult;
import io.github.kathukyabrian.util.DarajaUtil;
import io.github.kathukyabrian.util.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Mpesa {
    private static final Logger logger = LogManager.getLogger(Mpesa.class);

    public static MpesaSTKResponse requestPayment(Integer amount, String phoneNumber, String accountRef, String transactionDesc) {
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
            return new MpesaSTKResponse(successResponse.getMerchantRequestId(), successResponse.getResponseCode(), successResponse.getResponseDescription(), successResponse.getResponseCode().equals("0"));
        }

        if (errorResponse != null) {
            return new MpesaSTKResponse(errorResponse.getMerchantRequestId(), errorResponse.getResponseCode(), errorResponse.getResponseDescription(), false);
        }

        return null;
    }

    public static MpesaPaymentResult handleResult(MpesaResult mpesaResult) {
        MpesaPaymentResult result = new MpesaPaymentResult();

        DarajaSTKCallBack callBack = mpesaResult.getBody().getStkCallBack();
        result.setMerchantRequestId(callBack.getMerchantRequestId());
        result.setCode(callBack.getResultCode());
        result.setDescription(callBack.getResultDescription());

        if (mpesaResult.getBody().getStkCallBack().getCallBackMetaData() != null) {
            for (CallBackItem callBackItem : mpesaResult.getBody().getStkCallBack().getCallBackMetaData().getItem()) {
                if (callBackItem.getName().equals(ServiceConstants.MPESA_CALLBACK_REF_NO_KEY)) {
                    result.setTransactionRef((String) callBackItem.getValue());
                }

                if (callBackItem.getName().equals(ServiceConstants.MPESA_CALLBACK_AMOUNT_KEY)) {
                    result.setAmount((Double) callBackItem.getValue());
                }

                if (callBackItem.getName().equals(ServiceConstants.MPESA_CALLBACK_PHONE_NUMBER_KEY)) {
                    result.setPhoneNumber(callBackItem.getValue() + "");
                }
            }
        }
        result.setSuccess(result.getCode().equals(0));

        return result;
    }
}
