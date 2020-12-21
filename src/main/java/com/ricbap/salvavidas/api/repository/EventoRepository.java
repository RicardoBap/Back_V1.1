package com.ricbap.salvavidas.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricbap.salvavidas.api.model.Evento;
import com.ricbap.salvavidas.api.repository.evento.EventoRepositoryQuery;

public interface EventoRepository extends JpaRepository<Evento, Long>, EventoRepositoryQuery {
	
	public List<Evento> findByDataHoraEventoAfterAndDataReferenciaIsNotNullAndDestaqueTrue(LocalDateTime data);
	
	//LessThanEqualAndDataPagamentoIsNull  GreaterThan
	
	public List<Evento> findByDataHoraEventoAfter(LocalDateTime data);


}
