package tech.jambri.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MpesaSTKResponse {
    private String merchantRequestId;
    private String responseCode;
    private String responseDescription;
    private boolean success;
}
