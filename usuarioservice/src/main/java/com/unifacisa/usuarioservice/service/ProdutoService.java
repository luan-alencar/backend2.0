package com.unifacisa.usuarioservice.service;

import com.unifacisa.usuarioservice.domain.Produto;
import com.unifacisa.usuarioservice.repository.ProdutoRepository;
import com.unifacisa.usuarioservice.service.dto.ProdutoDTO;
import com.unifacisa.usuarioservice.service.mapper.ProdutoMapper;
import com.unifacisa.usuarioservice.utils.CrudUtils;
import com.unifacisa.usuarioservice.utils.exceptions.CustomServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService implements CrudUtils<Produto, ProdutoDTO> {

    private final WebClient webClient;
    private final ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoDTO> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoMapper.INSTANTE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO salvar(Produto produto) {
        Produto novoProduto = produtoRepository.save(produto);
        return ProdutoMapper.INSTANTE.toDto(novoProduto);
    }

    @Override
    public ProdutoDTO buscar(Long id) {
        return ProdutoMapper.INSTANTE.toDto(produtoRepository.getById(id));
    }

    @Override
    public ProdutoDTO editar(Produto produto) {
        if (produtoRepository.existsById(produto.getId())) {
            produto.setId(produto.getId());
            return this.salvar(produto);
        } else {
            throw new CustomServerErrorException("Produto não encontrado para o ID: " + produto.getId());
        }
    }

    @Override
    public void deletar(Long id) {
        this.produtoRepository.deleteById(id);
    }

    private Mono<? extends Throwable> handler5xxServerError(ClientResponse clientResponse) {
        return Mono.error(new CustomServerErrorException("Erro do server"));
    }

}