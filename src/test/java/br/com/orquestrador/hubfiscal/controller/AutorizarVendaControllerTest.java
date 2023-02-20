package br.com.orquestrador.hubfiscal.controller;

import br.com.orquestrador.hubfiscal.controller.response.VendaResponse;
import br.com.orquestrador.hubfiscal.exception.ClienteRequestException;
import br.com.orquestrador.hubfiscal.exception.ItemRequestException;
import br.com.orquestrador.hubfiscal.exception.OrdemPedidoRequestException;
import br.com.orquestrador.hubfiscal.exception.VendaRequestException;
import br.com.orquestrador.hubfiscal.service.AutorizarVendaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AutorizarVendaControllerTest {

    private static final String MENSAGEM_VENDA_EXCEPTION = "canal não deve estar em branco, canal não deve ser nulo, cliente não deve ser nulo, empresa não deve estar em branco, empresa não deve ser nulo, itens não deve estar vazio, itens não deve ser nulo, loja não deve estar em branco, loja não deve ser nulo, ordemPedido não deve ser nulo, quantidadeItens não deve ser nulo, totalItens não deve ser nulo.";
    private static final String MENSAGEM_ORDEM_PEDIDO_EXCEPTION = "ordemPedido.dataAutorizacao não deve ser nulo, ordemPedido.numeroOrdemExterno não deve estar em branco, ordemPedido.numeroOrdemExterno não deve ser nulo, ordemPedido.numeroPedido não deve estar em branco, ordemPedido.numeroPedido não deve ser nulo.";
    private static final String MENSAGEM_CLIENTE_EXCEPTION = "cliente.bairro não deve estar em branco, cliente.bairro não deve ser nulo, cliente.cep não deve estar em branco, cliente.cep não deve ser nulo, cliente.cidade não deve estar em branco, cliente.cidade não deve ser nulo, cliente.codigoIbge não deve estar em branco, cliente.codigoIbge não deve ser nulo, cliente.documento não deve estar em branco, cliente.documento não deve ser nulo, cliente.email não deve estar em branco, cliente.email não deve ser nulo, cliente.endereco não deve estar em branco, cliente.endereco não deve ser nulo, cliente.estado não deve estar em branco, cliente.estado não deve ser nulo, cliente.id não deve estar em branco, cliente.id não deve ser nulo, cliente.nome não deve estar em branco, cliente.nome não deve ser nulo, cliente.numeroEndereco não deve estar em branco, cliente.numeroEndereco não deve ser nulo, cliente.pais não deve estar em branco, cliente.pais não deve ser nulo, cliente.tipoDocumento não deve estar em branco, cliente.tipoDocumento não deve ser nulo, cliente.tipoPessoa não deve ser nulo.";
    private static final String MENSAGEM_ITENS_EXCEPTION = "itens[0].quantidade não deve ser nulo, itens[0].sku não deve ser nulo, itens[0].valor não deve ser nulo.";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutorizarVendaService autorizarVendaService;

    @Test
    void deveRetornarStatusVendaEmProcessamentoEStatusHttpCreatedQuandoChamarAutorizarVenda() throws Exception {
        Mockito.when(autorizarVendaService.autorizarVenda(Mockito.any())).thenReturn(mockRespose());
        this.mockMvc.perform(post("/autorizar-venda")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockRequest())
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("EM_PROCESSAMENTO"))
                .andExpect(content().json(jsonEsperado()));
    }

    @Test
    void deveRetornarStatusHttpBadRequestQuandoChamarAutorizarVendaEOcorrerVendaRequestException() throws Exception {
        Mockito.when(autorizarVendaService.autorizarVenda(Mockito.any())).thenThrow(new VendaRequestException(MENSAGEM_VENDA_EXCEPTION));
        this.mockMvc.perform(post("/autorizar-venda")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockRequest())
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(MENSAGEM_VENDA_EXCEPTION));
    }

    @Test
    void deveRetornarStatusHttpBadRequestQuandoChamarAutorizarVendaEOcorrerOrdemPedidoRequestException() throws Exception {
        Mockito.when(autorizarVendaService.autorizarVenda(Mockito.any())).thenThrow(new OrdemPedidoRequestException(MENSAGEM_ORDEM_PEDIDO_EXCEPTION));
        this.mockMvc.perform(post("/autorizar-venda")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockRequest())
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(MENSAGEM_ORDEM_PEDIDO_EXCEPTION));
    }

    @Test
    void deveRetornarStatusHttpBadRequestQuandoChamarAutorizarVendaEOcorrerClienteRequestException() throws Exception {
        Mockito.when(autorizarVendaService.autorizarVenda(Mockito.any())).thenThrow(new ClienteRequestException(MENSAGEM_CLIENTE_EXCEPTION));
        this.mockMvc.perform(post("/autorizar-venda")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockRequest())
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(MENSAGEM_CLIENTE_EXCEPTION));
    }

    @Test
    void deveRetornarStatusHttpBadRequestQuandoChamarAutorizarVendaEOcorrerItemRequestException() throws Exception {
        Mockito.when(autorizarVendaService.autorizarVenda(Mockito.any())).thenThrow(new ItemRequestException(MENSAGEM_ITENS_EXCEPTION));
        this.mockMvc.perform(post("/autorizar-venda")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockRequest())
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(MENSAGEM_ITENS_EXCEPTION));
    }

    private String jsonEsperado() {
        return "{\"status\":\"EM_PROCESSAMENTO\",\"dataResposta\":\"2022-11-11T15:47:10\"}";
    }

    private VendaResponse mockRespose() {
        return VendaResponse.builder()
                .status("EM_PROCESSAMENTO")
                .dataResposta("2022-11-11T15:47:10")
                .build();
    }

    private String mockRequest() {
        return "{\n" +
                "    \"canal\": \"APP\",\n" +
                "    \"empresa\": \"00001\",\n" +
                "    \"loja\": \"0001\",\n" +
                "    \"pdv\": 501,\n" +
                "    \"ordemPedido\": {\n" +
                "        \"numeroPedido\": \"200010710363\",\n" +
                "        \"numeroOrdemExterno\": \"2312529489023-1\",\n" +
                "        \"dataAutorizacao\": \"2022-11-11T15:37:56.194\"\n" +
                "    },\n" +
                "    \"cliente\": {\n" +
                "        \"id\": \"123456\",\n" +
                "        \"nome\": \"Givaldo Santos Vasconcelos\",\n" +
                "        \"documento\": \"70420816097\",\n" +
                "        \"tipoDocumento\": \"CPF\",\n" +
                "        \"tipoPessoa\": \"F\",\n" +
                "        \"endereco\": \"Travessa Francisco Vieira\",\n" +
                "        \"numeroEndereco\": \"11\",\n" +
                "        \"complementoEndereco\": \"Apto 405\",\n" +
                "        \"bairro\": \"Trapiche da Barra\",\n" +
                "        \"cidade\": \"Maceió\",\n" +
                "        \"estado\": \"AL\",\n" +
                "        \"pais\": \"BR\",\n" +
                "        \"cep\": \"57010460\",\n" +
                "        \"codigoIbge\": \"7162435\",\n" +
                "        \"telefone\": \"(82) 36774-7713\",\n" +
                "        \"email\": \"givaldo.santos.vasconcelos@gmail.com\"\n" +
                "    },\n" +
                "    \"totalItens\": 38744,\n" +
                "    \"quantidadeItens\": 6,\n" +
                "    \"itens\": [{\n" +
                "            \"sku\": 547170100,\n" +
                "            \"quantidade\": 3,\n" +
                "            \"valor\": 5691\n" +
                "        }, {\n" +
                "            \"sku\": 557882194,\n" +
                "            \"quantidade\": 2,\n" +
                "            \"valor\": 7990\n" +
                "        }, {\n" +
                "            \"sku\": 557282711,\n" +
                "            \"quantidade\": 1,\n" +
                "            \"valor\": 5691\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}
