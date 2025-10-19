package com.example.firstSub.controller;

import com.example.firstSub.model.request.TransactionRequestModel;
import com.example.firstSub.model.response.DebtInfoResponseModel;
import com.example.firstSub.model.response.TransactionResponseModel;
import com.example.firstSub.service.MIcroCreditService;
import com.example.firstSub.service.TransactionListServise;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController @Tag(name = "Micro Credit APIs")
public class MicroCreditController
{
    @Value("${stub.delay:1}")
    private int delay;

    MIcroCreditService servise = new MIcroCreditService();
    TransactionListServise store = new TransactionListServise();

    ///GET /v2/checkAccount?acc=1234567890&days=2
    @GetMapping(path = "/v2/checkAccount")
    @Operation(summary = "Получение информации о задолженностях по карте клиента")
    public ResponseEntity<?> getDebtInfo(@RequestParam("acc") String account, @RequestParam("days") int days) throws InterruptedException
    {
        DebtInfoResponseModel debtInfoResponse = servise.getDebtInfo(account, days);
        Thread.sleep(delay * 1);
        return new ResponseEntity<>(debtInfoResponse, HttpStatus.ACCEPTED);
    }

    ///POST /v2/payment
    @PostMapping(path = "/v2/payment")
    @Operation(summary = "Подтверждение транзакции")
    @Parameter(name = "BankCode", description = "Bank code", required = true, in = ParameterIn.HEADER)
    public ResponseEntity<?> postConfirmPayment(@RequestBody TransactionRequestModel request,
                                                @RequestHeader HttpHeaders headers) throws InterruptedException
    {

        int bankCode = Integer.parseInt(headers.get("BankCode").getFirst().toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("ProcessingTIme", LocalDateTime.now().format(formatter));
        String transaction_id = request.getTransaction_id();
        TransactionResponseModel transactionResponseModel = servise.getTransactionInfo(transaction_id, bankCode);
        store.Add(transactionResponseModel);
        Thread.sleep(delay * 1);
        return new ResponseEntity<>(transactionResponseModel, responseHeader, HttpStatus.OK);
    }

    @GetMapping(path = "/v2/payment")
    @Operation(summary = "Список всех транзакций")
    public ResponseEntity<?> getTransactions() throws InterruptedException
    {
        Thread.sleep(delay * 1);
        return new ResponseEntity<>(store.getAll(), HttpStatus.OK);
    }


    /// DELETE /v1/transactions/cleare/{id}
    @DeleteMapping(path = "/v1/transactions/cleare/{id}")
    @Operation(summary = "Удаление транзакции")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") String id) throws InterruptedException
    {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("ContentType", MediaType.TEXT_PLAIN_VALUE);
        store.deleteTransaction(id);
        Thread.sleep(delay * 1);
        return new ResponseEntity<>("deleted success",responseHeader, HttpStatus.OK);
    }

}
