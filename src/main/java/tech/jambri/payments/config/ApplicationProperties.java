package tech.jambri.payments.config;

public class ApplicationProperties {
    private String shortCode;
    private String transactionType;
    private String identifier;
    private String passKey;
    private String callbackUrl;
    private String consumerKey;
    private String consumerSecret;
    private String authUrl;
    private String paymentUrl;

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassKey() {
        return passKey;
    }

    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "shortCode='" + shortCode + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", identifier='" + identifier + '\'' +
                ", passKey='" + passKey + '\'' +
                ", callbackUrl='" + callbackUrl + '\'' +
                ", consumerKey='" + consumerKey + '\'' +
                ", consumerSecret='" + consumerSecret + '\'' +
                ", authUrl='" + authUrl + '\'' +
                ", paymentUrl='" + paymentUrl + '\'' +
                '}';
    }
}
