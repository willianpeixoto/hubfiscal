package br.com.orquestrador.hubfiscal.service;

import br.com.orquestrador.hubfiscal.controller.request.ClienteRequest;
import br.com.orquestrador.hubfiscal.controller.request.ItemRequest;
import br.com.orquestrador.hubfiscal.controller.request.OrdemPedidoRequest;
import br.com.orquestrador.hubfiscal.controller.request.VendaRequest;
import br.com.orquestrador.hubfiscal.controller.response.VendaResponse;
import br.com.orquestrador.hubfiscal.exception.ClienteRequestException;
import br.com.orquestrador.hubfiscal.exception.ItemRequestException;
import br.com.orquestrador.hubfiscal.exception.OrdemPedidoRequestException;
import br.com.orquestrador.hubfiscal.exception.VendaRequestException;
import br.com.orquestrador.hubfiscal.repository.AutorizarVendaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AutorizarVendaServiceTest {

    @Mock
    private AutorizarVendaRepository autorizarVendaRepository;

    @InjectMocks
    private AutorizarVendaService service;

    @Test
    void deveRetornarVendaResponseQuandoChamarAutorizarVenda() {
        VendaResponse resposta = service.autorizarVenda(mockVendaRequest());
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(respostaEsperada().getStatus(), resposta.getStatus());
        Assertions.assertEquals(respostaEsperada().getDataResposta(), resposta.getDataResposta());
    }

    @Test
    void deveRetornarExcecaoQuandoChamarAutorizarVendaComDadosDeVendaNulos() {
        VendaRequest vendaRequest = mockVendaRequest();
        vendaRequest.setCanal("");
        vendaRequest.setEmpresa("");
        vendaRequest.setLoja("");
        vendaRequest.setPdv(null);
        vendaRequest.setTotalItens(null);
        vendaRequest.setQuantidadeItens(null);
        Assertions.assertThrows(VendaRequestException.class, () -> service.autorizarVenda(vendaRequest));
    }

    @Test
    void deveRetornarExcecaoQuandoChamarAutorizarVendaComDadosDeOrdemPedidoNulos() {
        VendaRequest vendaRequest = mockVendaRequest();
        vendaRequest.setOrdemPedido(null);
        Assertions.assertThrows(OrdemPedidoRequestException.class, () -> service.autorizarVenda(vendaRequest));
    }

    @Test
    void deveRetornarExcecaoQuandoChamarAutorizarVendaComDadosDeClienteNulos() {
        VendaRequest vendaRequest = mockVendaRequest();
        vendaRequest.setCliente(null);
        Assertions.assertThrows(ClienteRequestException.class, () -> service.autorizarVenda(vendaRequest));
    }

    @Test
    void deveRetornarExcecaoQuandoChamarAutorizarVendaComListaDeItensNulos() {
        VendaRequest vendaRequest = mockVendaRequest();
        vendaRequest.setItens(null);
        Assertions.assertThrows(ItemRequestException.class, () -> service.autorizarVenda(vendaRequest));
    }

    @Test
    void deveRetornarExcecaoQuandoChamarAutorizarVendaComDadosDeItensNulos() {
        VendaRequest vendaRequest = mockVendaRequest();
        List<ItemRequest> itens = new ArrayList<>();
        itens.add(mockItem());
        itens.add(new ItemRequest());
        vendaRequest.setItens(itens);
        Assertions.assertThrows(ItemRequestException.class, () -> service.autorizarVenda(vendaRequest));
    }

    private VendaRequest mockVendaRequest() {
        VendaRequest vendaRequest = new VendaRequest();
        vendaRequest.setCanal("APP");
        vendaRequest.setEmpresa("00001");
        vendaRequest.setLoja("0001");
        vendaRequest.setPdv(501);
        vendaRequest.setTotalItens(38744);
        vendaRequest.setQuantidadeItens(6);
        vendaRequest.setOrdemPedido(mockOrdemPedido());
        vendaRequest.setCliente(mockCliente());
        vendaRequest.setItens(mockItens());
        return vendaRequest;
    }

    private OrdemPedidoRequest mockOrdemPedido() {
        OrdemPedidoRequest ordemPedidoRequest = new OrdemPedidoRequest();
        ordemPedidoRequest.setNumeroPedido("123456");
        ordemPedidoRequest.setNumeroOrdemExterno("123456");
        ordemPedidoRequest.setDataAutorizacao("2022-10-11T15:37:56.194");
        return ordemPedidoRequest;
    }

    private ClienteRequest mockCliente() {
        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setId("123456");
        clienteRequest.setNome("joao da silva");
        clienteRequest.setDocumento("12312312312");
        clienteRequest.setTipoDocumento("CPF");
        clienteRequest.setTipoPessoa("F");
        clienteRequest.setEndereco("Rua Teste");
        clienteRequest.setNumeroEndereco("51");
        clienteRequest.setComplementoEndereco("Apto 3");
        clienteRequest.setBairro("Bairro Teste");
        clienteRequest.setCidade("Cidade Teste");
        clienteRequest.setEstado("RS");
        clienteRequest.setPais("BR");
        clienteRequest.setCep("12312123");
        clienteRequest.setCodigoIbge("7162435");
        clienteRequest.setTelefone("(11) 12345-1234");
        clienteRequest.setEmail("teste@teste.com.br");
        return clienteRequest;
    }

    private List<ItemRequest> mockItens() {
        return Arrays.asList(mockItem());
    }

    private ItemRequest mockItem() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setSku(547170100);
        itemRequest.setQuantidade(3);
        itemRequest.setValor(new BigDecimal("5691"));
        return itemRequest;
    }

    private VendaResponse respostaEsperada() {
        return VendaResponse.builder()
                .status("EM_PROCESSAMENTO")
                .build();
    }
}
