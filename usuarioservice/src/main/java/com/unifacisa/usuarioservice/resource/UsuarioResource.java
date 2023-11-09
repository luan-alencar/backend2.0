package com.unifacisa.usuarioservice.resource;

import com.unifacisa.usuarioservice.service.UsuarioService;
import com.unifacisa.usuarioservice.service.dto.AcaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.unifacisa.usuarioservice.utils.ConstantsUtils.API_URL_USUARIOS;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_USUARIOS)
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @GetMapping("/buscar-acoes/{usuarioID}")
    public ResponseEntity<List<AcaoDTO>> buscarAcoesDisponiveis(@PathVariable("usuarioID") Long usuarioID) {
        return ResponseEntity.ok(usuarioService.verificarAcoesDisponiveis(usuarioID));
    }

}
