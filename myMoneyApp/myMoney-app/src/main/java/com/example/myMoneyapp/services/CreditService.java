package com.example.myMoneyapp.services;

import com.example.myMoneyapp.models.CreditModal;
import com.example.myMoneyapp.repositories.CreditRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    
    final CreditRepository creditRepository;
    
    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }
    
    @Transactional
    public CreditModal save(CreditModal creditModal) {
        return  creditRepository.save(creditModal);
    }
}
