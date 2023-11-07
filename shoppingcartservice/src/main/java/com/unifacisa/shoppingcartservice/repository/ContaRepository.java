package com.unifacisa.shoppingcartservice.repository;

import com.unifacisa.shoppingcartservice.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
