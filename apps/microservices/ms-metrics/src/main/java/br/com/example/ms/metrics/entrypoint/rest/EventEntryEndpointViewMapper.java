package br.com.example.ms.metrics.entrypoint.rest;

import br.com.example.ms.metrics.core.regulatory.outages.Endpoint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventEntryEndpointViewMapper {

	EventEntryEndpointViewMapper INSTANCE  = Mappers.getMapper(EventEntryEndpointViewMapper.class);

	EventEntryEndpointView from(Endpoint endpoint);

}
