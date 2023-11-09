package com.unifacisa.shoppingcartservice.resource;


import com.unifacisa.shoppingcartservice.domain.Acao;
import com.unifacisa.shoppingcartservice.service.AcaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_ACOES;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_ACOES)
public class AcaoResource {

    private final AcaoService acaoService;

    @GetMapping
    public ResponseEntity<List<Acao>> listar() {
        return ResponseEntity.ok(acaoService.listar());
    }

    @GetMapping("/{valor}")
    public ResponseEntity<List<Acao>> buscar(@PathVariable("valor") Double valor) {
        return ResponseEntity.ok(acaoService.buscarAcoesCompraveis(valor));
    }
}