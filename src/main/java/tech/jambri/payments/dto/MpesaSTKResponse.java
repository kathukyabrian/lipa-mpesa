package tech.jambri.payments.dto;

public class MpesaSTKResponse {
    private String merchantRequestId;
    private String responseCode;
    private String responseDescription;
    private boolean success;

    public MpesaSTKResponse() {
    }

    public MpesaSTKResponse(String merchantRequestId, String responseCode, String responseDescription, boolean success) {
        this.merchantRequestId = merchantRequestId;
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.success = success;
    }

    public String getMerchantRequestId() {
        return merchantRequestId;
    }

    public void setMerchantRequestId(String merchantRequestId) {
        this.merchantRequestId = merchantRequestId;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "MpesaSTKResponse{" +
                "merchantRequestId='" + merchantRequestId + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseDescription='" + responseDescription + '\'' +
                ", success=" + success +
                '}';
    }
}
