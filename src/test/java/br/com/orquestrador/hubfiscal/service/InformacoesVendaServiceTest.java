package br.com.orquestrador.hubfiscal.service;

import br.com.orquestrador.hubfiscal.entity.InformacoesVenda;
import br.com.orquestrador.hubfiscal.repository.InformacoesVendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InformacoesVendaServiceTest {

    @Mock
    private InformacoesVendaRepository informacoesVendaRepository;

    @InjectMocks
    private InformacoesVendaService service;

    @Test
    void deveExecutarSaveQuandoChamarGravaAtualizaInformacoesVenda() {
        InformacoesVenda informacoesVenda = new InformacoesVenda();
        service.gravaAtualizaInformacoesVenda(informacoesVenda);
        Mockito.verify(informacoesVendaRepository, Mockito.times(1)).save(informacoesVenda);
    }

    @Test
    void deveExecutarFindAllQuandoChamarListaTodasInformacoesVenda() {
        service.listaTodasInformacoesVenda();
        Mockito.verify(informacoesVendaRepository, Mockito.times(1)).findAll();
    }
}
