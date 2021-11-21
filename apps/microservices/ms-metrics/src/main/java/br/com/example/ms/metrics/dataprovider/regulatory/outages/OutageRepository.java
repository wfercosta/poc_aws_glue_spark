package br.com.example.ms.metrics.dataprovider.regulatory.outages;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface OutageRepository extends PagingAndSortingRepository<OutageEventTable, Long> {
}
