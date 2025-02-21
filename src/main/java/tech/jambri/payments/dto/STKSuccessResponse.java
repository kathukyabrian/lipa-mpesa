package tech.jambri.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class STKSuccessResponse {

    @JsonProperty("MerchantRequestID")
    private String merchantRequestId;

    @JsonProperty("CheckoutRequestID")
    private String checkOutRequestId;

    @JsonProperty("ResponseCode")
    private String responseCode;

    @JsonProperty("ResponseDescription")
    private String responseDescription;

    @JsonProperty("CustomerMessage")
    private String customerMessage;

    public String getMerchantRequestId() {
        return merchantRequestId;
    }

    public void setMerchantRequestId(String merchantRequestId) {
        this.merchantRequestId = merchantRequestId;
    }

    public String getCheckOutRequestId() {
        return checkOutRequestId;
    }

    public void setCheckOutRequestId(String checkOutRequestId) {
        this.checkOutRequestId = checkOutRequestId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    @Override
    public String toString() {
        return "STKSuccessResponse{" +
                "merchantRequestId='" + merchantRequestId + '\'' +
                ", checkOutRequestId='" + checkOutRequestId + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseDescription='" + responseDescription + '\'' +
                ", customerMessage='" + customerMessage + '\'' +
                '}';
    }
}
