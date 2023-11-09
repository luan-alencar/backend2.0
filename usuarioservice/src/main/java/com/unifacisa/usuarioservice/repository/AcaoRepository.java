package com.unifacisa.usuarioservice.repository;

import com.unifacisa.usuarioservice.domain.Acao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Long> {

    List<Acao> findByValorLessThanEqual(Double valor);
}
