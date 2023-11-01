package com.unifacisa.usuarioservice.repository;

import com.unifacisa.usuarioservice.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//    Optional<Usuario> findByCPF(String cpf);
}
