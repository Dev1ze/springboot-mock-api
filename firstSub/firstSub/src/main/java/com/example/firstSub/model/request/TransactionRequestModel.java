package com.example.firstSub.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class TransactionRequestModel
{
    private String transaction_id;
    private float sum;
    private Boolean need_processing;
}

/*
{
"transaction_id": "T-342-67777",
"sum": 133.12,
"need_processing": true
}

 */
