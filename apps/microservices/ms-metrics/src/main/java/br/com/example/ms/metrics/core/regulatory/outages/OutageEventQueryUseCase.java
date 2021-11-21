package br.com.example.ms.metrics.core.regulatory.outages;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutageEventQueryUseCase implements UseCase<PageResponse<List<OutageEventEntry>>, PageableRequest> {

	private final OutageEventGateway gateway;

	public OutageEventQueryUseCase(final OutageEventGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public PageResponse<List<OutageEventEntry>> execute(final PageableRequest pageable) {
		return gateway.findAll(pageable);
	}
}
