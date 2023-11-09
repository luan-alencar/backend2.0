package com.unifacisa.usuarioservice.repository;

import com.unifacisa.usuarioservice.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.conta.saldo >= :saldo")
    Usuario findBySaldoMaiorOuIgualA(@Param("saldo") Double saldo);

//    @Query("SELECT u FROM Usuario u WHERE u.conta.saldo >= :saldo")
//    Usuario findBySaldoMaiorOuIgualA(@Param("saldo") Double saldo);
}
