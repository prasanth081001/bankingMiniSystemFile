package com.example.bankingMiniSystem.DTOLayer;

import lombok.Data;

import java.util.List;

@Data
public class StatementResponse {
    private String accountNumber;
    private String customerName;
    private Double openingBalance;
    private Double closingBalance;
    private List<TransactionDTO> transactions;
}
