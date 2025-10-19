package com.example.firstSub.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class TransactionResponseModel
{
    private String transaction_id;
    private String bank_bik;
    private String status;
    private Contant contant;
}

/*
{
    "transaction_id": "T-342-67777",
    "bank_bik": "2345678997",
    "status": "accepted",
    "contact" : [
    {
        "name" : "HL pay company",
        "telecom" :[
            "43t5h7", k8fg534, 67hr333f, gj6iire
            ]
    }]
}
*/