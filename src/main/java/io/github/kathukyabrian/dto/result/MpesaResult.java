package io.github.kathukyabrian.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MpesaResult {

    @JsonProperty("Body")
    private DarajaCallBackBody body;

}
