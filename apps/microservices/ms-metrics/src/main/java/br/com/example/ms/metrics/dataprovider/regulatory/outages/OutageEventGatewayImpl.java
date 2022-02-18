package br.com.example.ms.metrics.dataprovider.regulatory.outages;

import br.com.example.ms.metrics.core.regulatory.outages.OutageEventEntry;
import br.com.example.ms.metrics.core.regulatory.outages.OutageEventGateway;
import br.com.example.ms.metrics.core.regulatory.outages.PageResponse;
import br.com.example.ms.metrics.core.regulatory.outages.PageableRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class OutageEventGatewayImpl implements OutageEventGateway {

	private final OutageRepository repository;
	private final OutageEventTableMapper mapper;

	public OutageEventGatewayImpl(final OutageRepository repository) {
		this.repository = repository;
		this.mapper = OutageEventTableMapper.INSTANCE;
	}

	@Override
	public PageResponse<List<OutageEventEntry>> findAll(final PageableRequest pageable) {
		final Page<OutageEventTable> page = repository.findAll(PageRequest.of(pageable.getPage(), pageable.getSize()));
		return PageResponse.of(mapper.from(page.getContent()), page.getTotalPages(), page.getTotalElements());
	}
}
