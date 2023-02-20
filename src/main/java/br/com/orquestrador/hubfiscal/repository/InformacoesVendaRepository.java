package br.com.orquestrador.hubfiscal.repository;

import br.com.orquestrador.hubfiscal.entity.InformacoesVenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface InformacoesVendaRepository extends CrudRepository<InformacoesVenda, BigDecimal> {
}
