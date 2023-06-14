package com.example.myMoneyapp.repositories;

import com.example.myMoneyapp.models.DebitModel;
import com.example.myMoneyapp.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DebitRepository extends JpaRepository<DebitModel, UUID> {
}
