package br.com.orquestrador.hubfiscal.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class InformacoesVendaDto {
    private BigDecimal id;
    private String canal;
    private Integer codigoEmpresa;
    private Integer codigoLoja;
    private Integer numeroPdv;
    private String numeroPedido;
    private String numeroOrdemExterno;
    private BigDecimal valorTotal;
    private BigDecimal qtdItem;
    private String vendaRequest;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataRequisicao;
    private String chaveNfe;
    private BigDecimal numeroNota;
    private LocalDateTime dataEmissao;
    private String pdf;
    private String situacao;
    private String motivo;
}
