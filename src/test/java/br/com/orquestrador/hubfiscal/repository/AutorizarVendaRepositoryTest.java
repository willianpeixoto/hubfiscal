package br.com.orquestrador.hubfiscal.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@ExtendWith(MockitoExtension.class)
public class AutorizarVendaRepositoryTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private AutorizarVendaRepository repository;

    @Test
    void deveExecutarMetododePublicacaoDeMensagemQuandoChamarAutorizaVenda() {
        this.repository.autorizaVenda("{jsonFake}");
        Mockito.verify(rabbitTemplate, Mockito.times(1)).send(Mockito.any(), Mockito.any());
    }

    @Test
    void naoDeveExecutarMetododePublicacaoDeMensagemQuandoChamarAutorizaVendaComUmaMensagemNula() {
        this.repository.autorizaVenda(null);
        Mockito.verify(rabbitTemplate, Mockito.times(0)).send(Mockito.any(), Mockito.any());
    }
}
