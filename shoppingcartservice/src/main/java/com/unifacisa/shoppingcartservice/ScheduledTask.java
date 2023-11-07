package com.unifacisa.shoppingcartservice;

import com.unifacisa.shoppingcartservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class ScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormate = new SimpleDateFormat("HH:mm:ss");

    private final ScheduleService scheduleService;

    @Scheduled(fixedRate = 5000)
    public void reportaCurrentTime() {
        String retorno = scheduleService.getTexto();
        log.info("The time is now {}", dateFormate.format(new Date()));
    }

    @Scheduled(fixedDelayString = "${interval}")
    public void reportaCurrentTime2() {
        log.info("Agora a hora é {}", dateFormate.format(new Date()));
    }

    @Scheduled(fixedDelayString = "${interval-in-cron}")
    public void reportaCurrentTime3() {
        String retorno = scheduleService.getTexto();
        log.info("The time is now {}", dateFormate.format(new Date()));
    }
}
