package com.unifacisa.shoppingcartservice.resource;


import com.unifacisa.shoppingcartservice.domain.Acao;
import com.unifacisa.shoppingcartservice.service.AcaoService;
import com.unifacisa.shoppingcartservice.utils.CrudResourceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_ACOES)
public class AcaoResource implements CrudResourceUtils<Acao> {

    private final AcaoService acaoService;

    @Override
    @GetMapping
    public ResponseEntity<List<Acao>> listar() {
        return ResponseEntity.ok(acaoService.listar());
    }

    @Override
    @PostMapping
    public ResponseEntity<Acao> salvar(@RequestBody Acao acao) {
        return ResponseEntity.ok().body(acaoService.salvar(acao));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Acao> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(acaoService.buscar(id));
    }

    @Override
    @PutMapping("/editar")
    public ResponseEntity<Acao> editar(@RequestBody Acao acao) {
        Acao entidade = acaoService.salvar(acao);
        return ResponseEntity.ok(entidade);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        acaoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/acoes-compraveis")
    public ResponseEntity<List<Acao>> obterAcoesCompraveis(@RequestParam("usuarioID") Long usuarioID, @RequestParam("accountBalance") Double accountBalance) {
        List<Acao> acoesCompraveis = acaoService.obterAcoesCompraveis(usuarioID, accountBalance);
        return ResponseEntity.ok(acoesCompraveis);
    }
}