package com.unifacisa.usuarioservice.resource;

import com.unifacisa.usuarioservice.domain.Produto;
import com.unifacisa.usuarioservice.service.ProdutoService;
import com.unifacisa.usuarioservice.service.dto.ProdutoDTO;
import com.unifacisa.usuarioservice.utils.CrudResourceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.unifacisa.usuarioservice.utils.ConstantsUtils.API_URL_PRODUTOS;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_PRODUTOS)
public class ProdutoResource implements CrudResourceUtils<Produto, ProdutoDTO> {

    private final ProdutoService produtoService;

    @Override
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@RequestBody Produto produto) {
        return ResponseEntity.created(URI.create(API_URL_PRODUTOS)).body(produtoService.salvar(produto));
    }

    @Override
    public ResponseEntity<ProdutoDTO> buscar(Long e) {
        return ResponseEntity.ok(produtoService.buscar(e));
    }

    @Override
    public ResponseEntity<ProdutoDTO> editar(Produto produto) {
        return ResponseEntity.ok(produtoService.editar(produto));
    }

}
