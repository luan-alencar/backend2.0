package com.unifacisa.shoppingcartservice.resource;


import com.unifacisa.shoppingcartservice.domain.Conta;
import com.unifacisa.shoppingcartservice.service.ContaService;
import com.unifacisa.shoppingcartservice.utils.CrudResourceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_CONTAS;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_CONTAS)
public class ContaResource implements CrudResourceUtils<Conta> {

    private final ContaService contaService;

    @Override
    @GetMapping
    public ResponseEntity<List<Conta>> listar() {
        return ResponseEntity.ok(contaService.listar());
    }

    @Override
    @PostMapping
    public ResponseEntity<Conta> salvar(@RequestBody Conta conta) {
        return ResponseEntity.ok().body(contaService.salvar(conta));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(contaService.buscar(id));
    }

    @Override
    @PutMapping("/editar")
    public ResponseEntity<Conta> editar(@RequestBody Conta conta) {
        Conta entidade = contaService.salvar(conta);
        return ResponseEntity.ok(entidade);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        contaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
