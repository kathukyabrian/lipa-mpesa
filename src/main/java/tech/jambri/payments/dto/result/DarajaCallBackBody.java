package tech.jambri.payments.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DarajaCallBackBody {

    @JsonProperty("stkCallback")
    private DarajaSTKCallBack stkCallBack;
}
