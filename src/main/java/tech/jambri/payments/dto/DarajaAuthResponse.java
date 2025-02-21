package tech.jambri.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DarajaAuthResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private String expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "DarajaAuthResponse{" +
                "accessToken='" + "********" + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                '}';
    }
}
