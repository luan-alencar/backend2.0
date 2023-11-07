package com.unifacisa.usuarioservice.resource;

import com.unifacisa.usuarioservice.domain.Conta;
import com.unifacisa.usuarioservice.service.ContaService;
import com.unifacisa.usuarioservice.service.dto.ContaDTO;
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
public class ContaResource implements CrudResourceUtils<Conta, ContaDTO> {

    private final ContaService contaService;

    @Override
    @GetMapping
    public ResponseEntity<List<ContaDTO>> listar() {
        return ResponseEntity.ok(contaService.listar());
    }

    @Override
    @PostMapping
    public ResponseEntity<ContaDTO> salvar(@RequestBody Conta conta) {
        return ResponseEntity.created(URI.create(API_URL_PRODUTOS)).body(contaService.salvar(conta));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(contaService.buscar(id));
    }

    @Override
    @PutMapping("/editar")
    public ResponseEntity<ContaDTO> editar(@RequestBody Conta conta) {
        ContaDTO produtoEditado = contaService.salvar(conta);
        return ResponseEntity.ok().body(produtoEditado);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        contaService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
