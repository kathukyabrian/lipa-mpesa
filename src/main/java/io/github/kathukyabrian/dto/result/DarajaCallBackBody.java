package io.github.kathukyabrian.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DarajaCallBackBody {

    @JsonProperty("stkCallback")
    private DarajaSTKCallBack stkCallBack;
}
