package com.example.myMoneyapp.services;

import com.example.myMoneyapp.models.CreditModel;
import com.example.myMoneyapp.repositories.CreditRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreditService {
    
    final CreditRepository creditRepository;
    
    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }
    
    @Transactional
    public CreditModel save(CreditModel creditModel) {
        return  creditRepository.save(creditModel);
    }
    
    public List<CreditModel> findAll() {
        return creditRepository.findAll();
    }
    
    public Optional<CreditModel> findById(UUID id) {
        return creditRepository.findById(id);
    }
    
    public void delete(CreditModel creditModel) {
        creditRepository.delete(creditModel);
    }
}
