package br.com.orquestrador.hubfiscal.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(schema = "HUB_FISCAL_OWNER", name = "VENDA_TESTE")
@SequenceGenerator(schema = "HUB_FISCAL_OWNER", name = "VENDA_TESTE_SEQ", sequenceName = "VENDA_TESTE_SEQ")
public class InformacoesVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VENDA_TESTE_SEQ")
    @Column(name = "ID", precision = 38)
    BigDecimal id;

    @Column(name = "CANAL", length = 100, nullable = false)
    String canal;

    @Column(name = "CODIGO_EMPRESA", nullable = false)
    Integer codigoEmpresa;

    @Column(name = "CODIGO_LOJA", nullable = false)
    Integer codigoLoja;

    @Column(name = "NUMERO_PDV", nullable = false)
    Integer numeroPdv;

    @Column(name = "NUMERO_PEDIDO", length = 38, nullable = false)
    String numeroPedido;

    @Column(name = "NUMERO_ORDEM_EXTERNO", length = 38, nullable = false)
    String numeroOrdemExterno;

    @Column(name = "VALOR_TOTAL", precision = 38, scale = 2, nullable = false)
    BigDecimal valorTotal;

    @Column(name = "QTD_ITEM", precision = 38, nullable = false)
    BigDecimal qtdItem;

    @Lob
    @Column(name = "VENDA_REQUEST", nullable = false)
    String vendaRequest;

    @Column(name = "DATA_ATUALIZACAO", nullable = false)
    LocalDateTime dataAtualizacao;

    @Column(name = "DATA_REQUISICAO", nullable = false)
    LocalDateTime dataRequisicao;

    @Column(name = "CHAVE_NFE", length = 44)
    String chaveNfe;

    @Column(name = "NUMERO_NOTA", precision = 38)
    BigDecimal numeroNota;

    @Column(name = "DATA_EMISSAO")
    LocalDateTime dataEmissao;

    @Lob
    @Column(name = "PDF")
    String pdf;

    @Column(name = "SITUACAO", length = 100, nullable = false)
    String situacao;

    @Column(name = "MOTIVO")
    String motivo;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public Integer getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Integer codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public Integer getCodigoLoja() {
        return codigoLoja;
    }

    public void setCodigoLoja(Integer codigoLoja) {
        this.codigoLoja = codigoLoja;
    }

    public Integer getNumeroPdv() {
        return numeroPdv;
    }

    public void setNumeroPdv(Integer numeroPdv) {
        this.numeroPdv = numeroPdv;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getNumeroOrdemExterno() {
        return numeroOrdemExterno;
    }

    public void setNumeroOrdemExterno(String numeroOrdemExterno) {
        this.numeroOrdemExterno = numeroOrdemExterno;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(BigDecimal qtdItem) {
        this.qtdItem = qtdItem;
    }

    public String getVendaRequest() {
        return vendaRequest;
    }

    public void setVendaRequest(String vendaRequest) {
        this.vendaRequest = vendaRequest;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public LocalDateTime getDataRequisicao() {
        return dataRequisicao;
    }

    public void setDataRequisicao(LocalDateTime dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public String getChaveNfe() {
        return chaveNfe;
    }

    public void setChaveNfe(String chaveNfe) {
        this.chaveNfe = chaveNfe;
    }

    public BigDecimal getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(BigDecimal numeroNota) {
        this.numeroNota = numeroNota;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
