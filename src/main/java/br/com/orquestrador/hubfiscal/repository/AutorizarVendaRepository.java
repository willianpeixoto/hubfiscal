package br.com.orquestrador.hubfiscal.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import org.springframework.amqp.core.MessageProperties;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AutorizarVendaRepository {

    private final RabbitTemplate rabbitTemplate;

    @Value("${hub-fiscal.queue}")
    private String queue;

    public void autorizaVenda(String mensagem) {
        if(mensagem != null) {
            Message message = new Message(mensagem.getBytes(), new MessageProperties());
            this.rabbitTemplate.send(queue, message);
            log.debug("Publicou mensagem: {}", mensagem);
        }
    }
}
