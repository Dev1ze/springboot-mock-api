package com.example.firstSub.service;

import com.example.firstSub.model.request.TransactionRequestModel;
import com.example.firstSub.model.response.TransactionResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionListServise
{
    private final List<TransactionResponseModel> responses = new ArrayList<>();
    public void Add(TransactionResponseModel responseModel){
        responses.add(responseModel);
    }

    public List<TransactionResponseModel> getAll(){
        return responses;
    }

    public void deleteTransaction(String id)
    {
        for(int i = 0; i < responses.size(); i++)
        {
            String trs = responses.get(i).getTransaction_id();
            if(id.equals(trs)){
                responses.remove(i);
            }

        }
    }

}
