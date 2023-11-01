package com.unifacisa.shoppingcartservice.repository;

import com.unifacisa.shoppingcartservice.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
