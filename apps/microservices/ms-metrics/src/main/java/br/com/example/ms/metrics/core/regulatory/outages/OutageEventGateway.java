package br.com.example.ms.metrics.core.regulatory.outages;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OutageEventGateway {
	PageResponse<List<OutageEventEntry>> findAll(PageableRequest pageable);
}
