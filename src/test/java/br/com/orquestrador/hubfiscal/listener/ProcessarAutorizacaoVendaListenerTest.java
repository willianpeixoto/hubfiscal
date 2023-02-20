package br.com.orquestrador.hubfiscal.listener;

import br.com.orquestrador.hubfiscal.service.ProcessarAutorizacaoVendaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProcessarAutorizacaoVendaListenerTest {

    @Mock
    private ProcessarAutorizacaoVendaService processarAutorizacaoVendaService;

    @InjectMocks
    private ProcessarAutorizacaoVendaListener listener;

    @Test
    void deveProcessarDeAutorizacaoDeVendaQuandoLerUmaMensagemValidaDaFila() {
        String mensagem = "{ mensagem }";
        listener.onProcessarAutorizacaoVenda(mensagem);
        Mockito.verify(processarAutorizacaoVendaService, Mockito.times(1)).processarAutorizacaoVenda(mensagem);
    }

    @Test
    void naoDeveProcessarDeAutorizacaoDeVendaQuandoLerUmaMensagemNulaDaFila() {
        String mensagem = null;
        listener.onProcessarAutorizacaoVenda(mensagem);
        Mockito.verify(processarAutorizacaoVendaService, Mockito.times(0)).processarAutorizacaoVenda(mensagem);
    }
}
