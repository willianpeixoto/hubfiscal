package br.com.orquestrador.hubfiscal.util;

import br.com.orquestrador.hubfiscal.controller.request.ClienteRequest;
import br.com.orquestrador.hubfiscal.controller.request.ItemRequest;
import br.com.orquestrador.hubfiscal.controller.request.OrdemPedidoRequest;
import br.com.orquestrador.hubfiscal.controller.request.VendaRequest;
import br.com.orquestrador.hubfiscal.exception.ClienteRequestException;
import br.com.orquestrador.hubfiscal.exception.ItemRequestException;
import br.com.orquestrador.hubfiscal.exception.OrdemPedidoRequestException;
import br.com.orquestrador.hubfiscal.exception.VendaRequestException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class VendaRequestValidatorUtil {

    public static final String MENSAGEM_VENDA_EXCEPTION = "canal não deve estar em branco, canal não deve ser nulo, cliente não deve ser nulo, empresa não deve estar em branco, empresa não deve ser nulo, itens não deve estar vazio, itens não deve ser nulo, loja não deve estar em branco, loja não deve ser nulo, ordemPedido não deve ser nulo, quantidadeItens não deve ser nulo, totalItens não deve ser nulo.";
    public static final String MENSAGEM_ORDEM_PEDIDO_EXCEPTION = "ordemPedido.dataAutorizacao não deve ser nulo, ordemPedido.numeroOrdemExterno não deve estar em branco, ordemPedido.numeroOrdemExterno não deve ser nulo, ordemPedido.numeroPedido não deve estar em branco, ordemPedido.numeroPedido não deve ser nulo.";
    public static final String MENSAGEM_CLIENTE_EXCEPTION = "cliente.bairro não deve estar em branco, cliente.bairro não deve ser nulo, cliente.cep não deve estar em branco, cliente.cep não deve ser nulo, cliente.cidade não deve estar em branco, cliente.cidade não deve ser nulo, cliente.codigoIbge não deve estar em branco, cliente.codigoIbge não deve ser nulo, cliente.documento não deve estar em branco, cliente.documento não deve ser nulo, cliente.email não deve estar em branco, cliente.email não deve ser nulo, cliente.endereco não deve estar em branco, cliente.endereco não deve ser nulo, cliente.estado não deve estar em branco, cliente.estado não deve ser nulo, cliente.id não deve estar em branco, cliente.id não deve ser nulo, cliente.nome não deve estar em branco, cliente.nome não deve ser nulo, cliente.numeroEndereco não deve estar em branco, cliente.numeroEndereco não deve ser nulo, cliente.pais não deve estar em branco, cliente.pais não deve ser nulo, cliente.tipoDocumento não deve estar em branco, cliente.tipoDocumento não deve ser nulo, cliente.tipoPessoa não deve ser nulo.";
    public static final String MENSAGEM_ITENS_EXCEPTION = "itens[0].quantidade não deve ser nulo, itens[0].sku não deve ser nulo, itens[0].valor não deve ser nulo.";

    public static void validaVenda(VendaRequest vendaRequest) {
        if(vendaRequest == null || isTodosAtributosNulosOuVazios(vendaRequest)) {
            throw new VendaRequestException(MENSAGEM_VENDA_EXCEPTION);
        }
    }

    private static boolean isTodosAtributosNulosOuVazios(VendaRequest vendaRequest) {
        return StringUtils.isAllBlank(vendaRequest.getCanal()
                                ,vendaRequest.getEmpresa()
                                ,vendaRequest.getLoja())
            && vendaRequest.getPdv() == null
            && vendaRequest.getTotalItens() == null
            && vendaRequest.getQuantidadeItens() == null;
    }

    public static void validaOrdemPedido(OrdemPedidoRequest ordemPedidoRequest) {
        if(ordemPedidoRequest == null || StringUtils.isAllBlank(
                ordemPedidoRequest.getNumeroPedido()
                ,ordemPedidoRequest.getNumeroOrdemExterno()
                ,ordemPedidoRequest.getDataAutorizacao()
        )) {
            throw new OrdemPedidoRequestException(MENSAGEM_ORDEM_PEDIDO_EXCEPTION);
        }
    }

    public static void validaCliente(ClienteRequest clienteRequest) {
        if(clienteRequest == null || StringUtils.isAllBlank(
                clienteRequest.getId()
                ,clienteRequest.getNome()
                ,clienteRequest.getDocumento()
                ,clienteRequest.getTipoDocumento()
                ,clienteRequest.getTipoPessoa()
                ,clienteRequest.getEndereco()
                ,clienteRequest.getNumeroEndereco()
                ,clienteRequest.getComplementoEndereco()
                ,clienteRequest.getBairro()
                ,clienteRequest.getCidade()
                ,clienteRequest.getEstado()
                ,clienteRequest.getPais()
                ,clienteRequest.getCep()
                ,clienteRequest.getCodigoIbge()
                ,clienteRequest.getTelefone()
                ,clienteRequest.getEmail()
        )) {
            throw new ClienteRequestException(MENSAGEM_CLIENTE_EXCEPTION);
        }
    }

    public static void validaItens(List<ItemRequest> itens) {
        if(itens == null || itens.isEmpty()) {
            throw new ItemRequestException(MENSAGEM_ITENS_EXCEPTION);
        }
        for(ItemRequest item : itens) {
            if(item.getSku() == null && item.getQuantidade() == null && item.getValor() == null) {
                throw new ItemRequestException(MENSAGEM_ITENS_EXCEPTION);
            }
        }
    }
}
