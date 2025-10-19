package com.example.firstSub.service;

import com.example.firstSub.model.request.TransactionRequestModel;
import com.example.firstSub.model.response.Contant;
import com.example.firstSub.model.response.Debt;
import com.example.firstSub.model.response.DebtInfoResponseModel;
import com.example.firstSub.model.response.TransactionResponseModel;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MIcroCreditService
{

    Random random = new Random();

    public DebtInfoResponseModel getDebtInfo(String account, int days){
        String[] description = {"car","phone","home","trip","pet","course","holiday"};
        DebtInfoResponseModel model = new DebtInfoResponseModel();

        model.setAccount(account);
        model.setVip_client(!(Integer.parseInt(account) % 2 == 0)); ///Проверка на четность
        model.setBlocked(false);
        model.setInn(account + "111");

        Debt[] debts = new Debt[days];
        for (int i = 0; i < days; i++){
            Debt debt = new Debt();
            debt.setSum(ThreadLocalRandom.current().nextInt(1000,10000));
            debt.setDescription(description[random.nextInt(description.length)]);

            debts[i] = debt;
        }
        model.setDebt(debts);
        return model;
    }

    public TransactionResponseModel getTransactionInfo(String transaction_id, int bankConde){
        TransactionResponseModel model = new TransactionResponseModel();

        StringBuilder sbBank_bik = new StringBuilder();
        for(int i = 0; i < ThreadLocalRandom.current().nextInt(10,16); i++)
        {
            sbBank_bik.append(random.nextInt(10));
        }

        Contant contant = new Contant();
        String chars = "abcdefghijklmnopqrstuvwxyz123456789";

        int sumNumberBankCode = 0;
        while (bankConde > 0)
        {
            int digit = bankConde % 10;
            sumNumberBankCode += digit;
            bankConde /= 10;
        }

        String[] telecom = new String[sumNumberBankCode];
        for(int j = 0; j < sumNumberBankCode; j++)
        {
            StringBuilder sbItemTelecom = new StringBuilder();
            for(int i = 0; i < ThreadLocalRandom.current().nextInt(4,8); i++){
                sbItemTelecom.append(chars.charAt(random.nextInt(chars.length())));
            }
            telecom[j] = sbItemTelecom.toString();
        }
        contant.setName("HL pay company");
        contant.setTelecom(telecom);

        model.setTransaction_id(transaction_id);
        model.setBank_bik(sbBank_bik.toString());
        model.setStatus("accepted");
        model.setContant(contant);

        return model;
    }
}
