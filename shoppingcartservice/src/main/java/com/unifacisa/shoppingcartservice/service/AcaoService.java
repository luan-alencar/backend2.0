package com.unifacisa.shoppingcartservice.service;

import com.unifacisa.shoppingcartservice.domain.Acao;
import com.unifacisa.shoppingcartservice.service.exceptions.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_ACOES;

@Service
@RequiredArgsConstructor
public class AcaoService {

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

    public List<Acao> buscarAcoesCompraveis(Double valor) {
        Flux<Acao> acaoFlux = webClient.get()
                .uri(API_URL_ACOES +"/{valor}", valor)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Acao.class)
                .onErrorResume(this::handleError);

        List<Acao> todosAcaos = acaoFlux.collectList().block();
        return todosAcaos;
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
