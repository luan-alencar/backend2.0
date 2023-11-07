package com.unifacisa.shoppingcartservice.service;

import com.unifacisa.shoppingcartservice.domain.Acao;
import com.unifacisa.shoppingcartservice.service.exceptions.GlobalExceptionHandler;
import com.unifacisa.shoppingcartservice.utils.CrudUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_ACOES;
import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_ACOES_ID;

@Service
@RequiredArgsConstructor
public class AcaoService implements CrudUtils<Acao> {

    private final WebClient webClient;

    public List<Acao> listar() {
        Flux<Acao> acaoFlux = webClient.get()
                .uri(API_URL_ACOES)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Acao.class)
                .onErrorResume(this::handleError);

        List<Acao> todosAcaos = acaoFlux.collectList().block();
        return todosAcaos;
    }

    @Override
    public Acao salvar(Acao acao) {
        Mono<Acao> acaoMono = webClient.post()
                .uri(API_URL_ACOES)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(acao)
                .retrieve()
                .bodyToMono(Acao.class);
//                .onErrorResume(this::handleError);
        Acao novaAcao = acaoMono.block();
        return novaAcao;
    }

    @Override
    public Acao buscar(Long id) {
        Mono<Acao> acaoMono = webClient.get()
                .uri(API_URL_ACOES_ID, id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Acao.class)
                .onErrorResume(this::handleError);
        Acao acaoEcontrada = acaoMono.block();
        return acaoEcontrada;
    }

    @Override
    public Acao editar(Acao acao) {
        Mono<Acao> produtoMono = webClient.put()
                .uri(API_URL_ACOES)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().
                bodyToMono(Acao.class);
//                .onErrorResume(this::handleError);
        Acao novaAcao = produtoMono.block();
        return novaAcao;
    }

    @Override
    public void deletar(Long id) {
        webClient.delete()
                .uri(API_URL_ACOES_ID, id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
//                .onErrorResume(this::handleErrorVoid);
    }

    public List<Acao> obterAcoesCompraveis(Long usuarioID, Double accountBalance) {
        return this.listar().stream()
                .filter(acao -> acao.getValor() <= accountBalance)
                .collect(Collectors.toList());
    }

    private Mono<? extends Acao> handleError(Throwable error) {
        return (error instanceof GlobalExceptionHandler)
                ? Mono.error(error)
                : Mono.error(new GlobalExceptionHandler());
    }

    private Mono<? extends Void> handleErrorVoid(Throwable error) {
        if (error instanceof GlobalExceptionHandler) {
            return Mono.error(error);
        } else {
            HttpStatusCode status = ((GlobalExceptionHandler) error).getStatusCode();
            String razao = "Erro de servidor";
            return Mono.error(new GlobalExceptionHandler(status, razao));
        }
    }
}
