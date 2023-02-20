package br.com.orquestrador.hubfiscal.config;


import br.com.orquestrador.hubfiscal.entity.InformacoesVenda;
import br.com.orquestrador.hubfiscal.service.InformacoesVendaService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataLoaderConfig {

    @Autowired
    InformacoesVendaService informacoesVendaService;

    @Bean
    InitializingBean insereVendaNaBase() {
        return () -> {
            InformacoesVenda informacoesVenda = new InformacoesVenda();
            informacoesVenda.setCanal("APP");
            informacoesVenda.setCodigoEmpresa(1);
            informacoesVenda.setCodigoLoja(1);
            informacoesVenda.setNumeroPdv(501);
            informacoesVenda.setNumeroPedido("200010710363");
            informacoesVenda.setNumeroOrdemExterno("2312529489023-1");
            informacoesVenda.setValorTotal(new BigDecimal("387.44"));
            informacoesVenda.setQtdItem(new BigDecimal("6"));
            informacoesVenda.setVendaRequest("jsonErro");
            informacoesVenda.setDataAtualizacao(LocalDateTime.parse("2022-11-11T15:47:10"));
            informacoesVenda.setDataRequisicao(LocalDateTime.parse("2022-11-11T15:37:56.194"));
            informacoesVenda.setSituacao("ERRO");
            informacoesVenda.setMotivo("Venda 2312529489023-1 recebida com erro");
            informacoesVendaService.gravaAtualizaInformacoesVenda(informacoesVenda);
        };
    }
}