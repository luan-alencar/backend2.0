package com.unifacisa.usuarioservice.service;

import com.unifacisa.usuarioservice.domain.Conta;
import com.unifacisa.usuarioservice.domain.Usuario;
import com.unifacisa.usuarioservice.repository.UsuarioRepository;
import com.unifacisa.usuarioservice.service.dto.AcaoDTO;
import com.unifacisa.usuarioservice.service.dto.UsuarioDTO;
import com.unifacisa.usuarioservice.service.mapper.UsuarioMapper;
import com.unifacisa.usuarioservice.utils.ScheduledTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final AcaoService acaoService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO salvar(Usuario usuario) {
        return UsuarioMapper.INSTANTE.toDto(usuario);
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        return UsuarioMapper.INSTANTE.toDto(usuario);
    }

    public List<AcaoDTO> verificarAcoesDisponiveis(Long usuarioID) {
        Usuario usuario = UsuarioMapper.INSTANTE.toEntity(buscarPorId(usuarioID));
        Conta conta = new Conta();
        conta.setSaldo(1353.0);
        usuario.setConta(conta);
        this.salvar(usuario);

        Double saldoConta = usuario.getConta().getSaldo();
        return new ArrayList<>(acaoService.buscar(saldoConta));
    }
}
