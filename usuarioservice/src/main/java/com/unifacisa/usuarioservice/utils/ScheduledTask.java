package com.unifacisa.usuarioservice.utils;

import com.unifacisa.usuarioservice.domain.Acao;
import com.unifacisa.usuarioservice.service.AcaoService;
import com.unifacisa.usuarioservice.service.ScheduleService;
import com.unifacisa.usuarioservice.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormate = new SimpleDateFormat("HH:mm:ss");

    private final AcaoService acaoService;
    private final UsuarioService usuarioService;
    private final ScheduleService scheduleService;

    @Scheduled(fixedRate = 50000)
    public void reportaCurrentTimeAcoes() throws Exception {
        List<Acao> acaoList = acaoService.buscarAcoesCompraveis();
        log.info("The time is now {}", dateFormate.format(new Date()));
    }

    @Scheduled(fixedRate = 50000)
    public void reportaCurrentTimeUsuario() throws Exception {
//        List<Acao> acaoList = acaoService.buscarAcoesCompraveis();
        log.info("The time is now {}", dateFormate.format(new Date()));
    }
}
