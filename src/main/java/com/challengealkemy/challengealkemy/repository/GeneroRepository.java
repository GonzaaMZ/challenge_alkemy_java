package com.challengealkemy.challengealkemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challengealkemy.challengealkemy.models.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>{
    
}
