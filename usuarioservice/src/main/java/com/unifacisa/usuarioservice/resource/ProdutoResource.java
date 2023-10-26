package com.unifacisa.usuarioservice.resource;

import com.unifacisa.usuarioservice.domain.Produto;
import com.unifacisa.usuarioservice.service.ProdutoService;
import com.unifacisa.usuarioservice.utils.CrudResourceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/produtos")
public class ProdutoResource implements CrudResourceUtils<Produto> {

    private final ProdutoService produtoService;

    @Override
    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @Override
    public ResponseEntity<Produto> salvar(Produto produto) {
        return null;
    }

    @Override
    public ResponseEntity<Produto> buscar(Long e) {
        return null;
    }

    @Override
    public ResponseEntity<Produto> editar(Produto produto) {
        return null;
    }
}
