package br.com.orquestrador.hubfiscal.service;

import br.com.orquestrador.hubfiscal.entity.InformacoesVenda;
import br.com.orquestrador.hubfiscal.repository.InformacoesVendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InformacoesVendaService {

    private final InformacoesVendaRepository informacoesVendaRepository;

    public List<InformacoesVenda> listaTodasInformacoesVenda() {
        List<InformacoesVenda> lista = new ArrayList<>();
        informacoesVendaRepository.findAll().forEach(informacoesVenda -> lista.add(informacoesVenda));
        return lista;
    }

    public void gravaAtualizaInformacoesVenda(InformacoesVenda informacoesVenda) {
        informacoesVendaRepository.save(informacoesVenda);
    }
}
