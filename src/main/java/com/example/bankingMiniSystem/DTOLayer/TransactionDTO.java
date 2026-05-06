package com.example.bankingMiniSystem.DTOLayer;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long transactionId;
    private String type;
    private String transactionCategory;
    private Double amount;
    private Double runningBalance;
    private String remarks;
    private String status;

}
