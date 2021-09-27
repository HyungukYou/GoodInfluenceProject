package com.example.hyungukservice.repository;

import com.example.hyungukservice.domain.HyunGuk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HyunGukRepository extends JpaRepository<HyunGuk,Long> {
}
