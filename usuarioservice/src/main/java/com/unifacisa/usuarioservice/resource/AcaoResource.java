package com.unifacisa.usuarioservice.resource;

import com.unifacisa.usuarioservice.domain.Acao;
import com.unifacisa.usuarioservice.service.AcaoService;
import com.unifacisa.usuarioservice.service.dto.AcaoDTO;
import com.unifacisa.usuarioservice.utils.CrudResourceUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.unifacisa.usuarioservice.utils.ConstantsUtils.API_URL_ACOES;
import static com.unifacisa.usuarioservice.utils.ConstantsUtils.API_URL_PRODUTOS;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_ACOES)
public class AcaoResource implements CrudResourceUtils<Acao, AcaoDTO> {

	@Autowired
    private AcaoService acaoService;

    @Override
    @GetMapping
    public ResponseEntity<List<AcaoDTO>> listar() {
        return ResponseEntity.ok(acaoService.listar());
    }

    @Override
    @PostMapping
    public ResponseEntity<AcaoDTO> salvar(@RequestBody Acao acao) {
        return ResponseEntity.created(URI.create(API_URL_PRODUTOS)).body(acaoService.salvar(acao));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AcaoDTO> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(acaoService.buscar(id));
    }

    @Override
    @PutMapping("/editar")
    public ResponseEntity<AcaoDTO> editar(@RequestBody Acao acao) {
        AcaoDTO acaoDTO = acaoService.salvar(acao);
        return ResponseEntity.ok().body(acaoDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        acaoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
