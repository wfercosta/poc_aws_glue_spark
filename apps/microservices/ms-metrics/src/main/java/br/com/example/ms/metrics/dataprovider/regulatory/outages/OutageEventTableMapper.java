package br.com.example.ms.metrics.dataprovider.regulatory.outages;

import br.com.example.ms.metrics.core.regulatory.outages.OutageEventEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {EndpointTableMapper.class})
public interface OutageEventTableMapper {

	OutageEventTableMapper INSTANCE = Mappers.getMapper(OutageEventTableMapper.class);

	OutageEventEntry from(OutageEventTable table);

	List<OutageEventEntry> from(List<OutageEventTable> tables);

}
