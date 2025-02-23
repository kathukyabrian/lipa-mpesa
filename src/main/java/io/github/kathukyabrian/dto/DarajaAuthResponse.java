package io.github.kathukyabrian.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DarajaAuthResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private String expiresIn;

    @Override
    public String toString() {
        return "DarajaAuthResponse{" +
                "accessToken='" + "********" + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                '}';
    }
}
