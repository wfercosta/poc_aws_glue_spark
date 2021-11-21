package br.com.example.ms.metrics.dataprovider.regulatory.outages;

import br.com.example.ms.metrics.core.regulatory.outages.Endpoint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EndpointTableMapper {
	EndpointTableMapper INSTANCE = Mappers.getMapper(EndpointTableMapper.class);

	Endpoint from(EndpointTable table);

	List<Endpoint> from(List<EndpointTable> tables);
}
