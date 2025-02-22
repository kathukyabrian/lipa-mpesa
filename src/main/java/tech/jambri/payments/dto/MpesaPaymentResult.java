package tech.jambri.payments.dto;

import lombok.Data;

@Data
public class MpesaPaymentResult {
    private String merchantRequestId;

    private Integer code;

    private String description;

    private Double amount;

    private String transactionRef;

    private String phoneNumber;

    private boolean success;

}
