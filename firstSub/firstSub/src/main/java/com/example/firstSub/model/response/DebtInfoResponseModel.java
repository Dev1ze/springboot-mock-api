package com.example.firstSub.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class DebtInfoResponseModel {
    private String account;
    @JsonProperty("vip-client")
    private Boolean vip_client;
    private Boolean blocked;
    private String inn;
    private Debt[] debt;
}

/*
{
    "account": "1234567890",
    "vip-client": false,
    "blocked": false,
    "inn": "1234567890111",
    "debt": [
    {
        "sum": 1000,
        "description": "parking"
    },
    {
        "sum": 3000,
        "description": "gkh"
    }]
}
 */
