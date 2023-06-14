package com.example.myMoneyapp.services;

import com.example.myMoneyapp.models.DebitModel;
import com.example.myMoneyapp.repositories.DebitRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DebitService {

    final DebitRepository debitRepository;
    
    public DebitService(DebitRepository debitRepository) {
        this.debitRepository = debitRepository;
    }
    
    @Transactional
    public DebitModel save(DebitModel debitModel) {
        return debitRepository.save(debitModel);
    }
}
