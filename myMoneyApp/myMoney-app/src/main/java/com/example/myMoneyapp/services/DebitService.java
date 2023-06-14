package com.example.myMoneyapp.services;

import com.example.myMoneyapp.models.DebitModel;
import com.example.myMoneyapp.repositories.DebitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DebitService {
    @Autowired
    final DebitRepository debitRepository;
    
    public DebitService(DebitRepository debitRepository) {
        this.debitRepository = debitRepository;
    }
    
    @Transactional
    public DebitModel save(DebitModel debitModel) {
        return debitRepository.save(debitModel);
    }
    
    public List<DebitModel> findAll() {
        return debitRepository.findAll();
    }
    
    public Optional<DebitModel> findById(UUID id) {
        return debitRepository.findById(id);
    }
    @Transactional
    public void delete(DebitModel debitModel) {
        debitRepository.delete(debitModel);
    }
}
