package io.github.kathukyabrian.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CallBackMetaData {

    @JsonProperty("Item")
    private List<CallBackItem> item;
}
