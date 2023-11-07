package com.unifacisa.shoppingcartservice.repository;

import com.unifacisa.shoppingcartservice.domain.Acao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Long> {
}
