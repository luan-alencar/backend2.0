package com.unifacisa.usuarioservice.resource;

import com.unifacisa.usuarioservice.domain.Acao;
import com.unifacisa.usuarioservice.service.AcaoService;
import com.unifacisa.usuarioservice.service.dto.AcaoDTO;
import com.unifacisa.usuarioservice.service.mapper.AcaoMapper;
import com.unifacisa.usuarioservice.utils.CrudResourceUtils;
import lombok.RequiredArgsConstructor;

import org.json.simple.JSONArray;
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
public class AcaoResource {

    @Autowired
    private AcaoService acaoService;

    @GetMapping
    public ResponseEntity<List<AcaoDTO>> listar() {
        return ResponseEntity.ok(acaoService.listar());
    }

    @GetMapping("/{valor}")
    public ResponseEntity<List<AcaoDTO>> buscar(@PathVariable("valor") Double valor) {
        return ResponseEntity.ok(acaoService.buscar(valor));
    }


}
