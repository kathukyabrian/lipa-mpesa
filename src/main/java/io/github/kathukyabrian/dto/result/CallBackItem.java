package io.github.kathukyabrian.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CallBackItem {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Value")
    private Object value;
}
