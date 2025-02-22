package tech.jambri.payments.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DarajaSTKCallBack {

    @JsonProperty("MerchantRequestID")
    private String merchantRequestId;

    @JsonProperty("CheckoutRequestID")
    private String checkOutRequestId;

    @JsonProperty("ResultCode")
    private Integer resultCode;

    @JsonProperty("ResultDesc")
    private String resultDescription;

    @JsonProperty("CallbackMetadata")
    private CallBackMetaData callBackMetaData;

}
