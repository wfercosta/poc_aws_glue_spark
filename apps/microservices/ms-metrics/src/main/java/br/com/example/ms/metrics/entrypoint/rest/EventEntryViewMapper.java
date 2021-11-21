package br.com.example.ms.metrics.entrypoint.rest;

import br.com.example.ms.metrics.core.regulatory.outages.OutageEventEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {EventEntryEndpointViewMapper.class})
public interface EventEntryViewMapper {

	EventEntryViewMapper INSTANCE  = Mappers.getMapper(EventEntryViewMapper.class);

	EventEntryView from(OutageEventEntry entry);

	List<EventEntryView> from(List<OutageEventEntry> entries);

}
