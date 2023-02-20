package br.com.orquestrador.hubfiscal.listener;

import br.com.orquestrador.hubfiscal.service.ProcessarAutorizacaoVendaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessarAutorizacaoVendaListener {

    private final ProcessarAutorizacaoVendaService processarAutorizacaoVendaService;

    @RabbitListener(queues = "autorizar-venda-queue")
    public void onProcessarAutorizacaoVenda(String messagem) {
        log.debug("Leu mensagem: {}", messagem);
        if(messagem != null) {
            processarAutorizacaoVendaService.processarAutorizacaoVenda(messagem);
        }
    }
}
