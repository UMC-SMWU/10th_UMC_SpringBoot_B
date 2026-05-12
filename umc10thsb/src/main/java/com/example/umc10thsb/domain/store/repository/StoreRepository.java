package com.example.umc10thsb.domain.store.repository;

import com.example.umc10thsb.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
