package com.unifacisa.shoppingcartservice.resource;


import com.unifacisa.shoppingcartservice.domain.Conta;
import com.unifacisa.shoppingcartservice.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_CONTAS;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_CONTAS)
public class ContaResource {

    private final ContaService contaService;

    @GetMapping
    public ResponseEntity<List<Conta>> listar() {
        return ResponseEntity.ok(contaService.listar());
    }

    @PostMapping
    public ResponseEntity<Conta> salvar(@RequestBody Conta conta) {
        return ResponseEntity.ok().body(contaService.salvar(conta));
    }


}
