package com.unifacisa.usuarioservice.repository;

import com.unifacisa.usuarioservice.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
