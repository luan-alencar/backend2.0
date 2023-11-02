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
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(produtoService.buscar(id));
    }

    @Override
    @PutMapping("/editar")
    public ResponseEntity<ProdutoDTO> editar(@RequestBody Produto produto) {
        ProdutoDTO produtoEditado = produtoService.salvar(produto);
        return ResponseEntity.ok().body(produtoEditado);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        produtoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
