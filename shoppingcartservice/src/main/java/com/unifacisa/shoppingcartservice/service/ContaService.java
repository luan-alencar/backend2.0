package com.unifacisa.shoppingcartservice.service;

import com.unifacisa.shoppingcartservice.domain.Conta;
import com.unifacisa.shoppingcartservice.service.exceptions.GlobalExceptionHandler;
import com.unifacisa.shoppingcartservice.utils.CrudUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.*;

@Service
@RequiredArgsConstructor
public class ContaService implements CrudUtils<Conta> {

    private final WebClient webClient;

    public List<Conta> listar() {
        Flux<Conta> produtosExternoFlux = webClient.get()
                .uri(API_URL_CONTAS)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Conta.class)
                .onErrorResume(this::handleError);

        List<Conta> todosContas = produtosExternoFlux.collectList().block();
        return todosContas;
    }

    @Override
    public Conta salvar(Conta conta) {
        Mono<Conta> produtoMono = webClient.post()
                .uri(API_URL_CONTAS)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(conta)
                .retrieve()
                .bodyToMono(Conta.class);
//                .onErrorResume(this::handleError);
        Conta novoConta = produtoMono.block();
        return novoConta;
    }

    @Override
    public Conta buscar(Long id) {
        Mono<Conta> produtoMono = webClient.get()
                .uri(API_URL_CONTAS_ID, id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Conta.class)
                .onErrorResume(this::handleError);
        Conta contaEcontrado = produtoMono.block();
        return contaEcontrado;
    }

    @Override
    public Conta editar(Conta conta) {
        Mono<Conta> produtoMono = webClient.put()
                .uri(API_URL_CONTAS)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().
                bodyToMono(Conta.class);
//                .onErrorResume(this::handleError);
        Conta novoConta = produtoMono.block();
        return novoConta;
    }

    @Override
    public void deletar(Long id) {
        webClient.delete()
                .uri(API_URL_CONTAS_ID, id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
//                .onErrorResume(this::handleErrorVoid);
    }

    private Mono<? extends Conta> handleError(Throwable error) {
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
