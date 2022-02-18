package br.com.example.ms.metrics.entrypoint.rest;

import br.com.example.ms.metrics.core.regulatory.outages.OutageEventEntry;
import br.com.example.ms.metrics.core.regulatory.outages.OutageEventQueryUseCase;
import br.com.example.ms.metrics.core.regulatory.outages.PageResponse;
import br.com.example.ms.metrics.core.regulatory.outages.PageableRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/discovery/v1/outages")
public class OutageController {

	private final OutageEventQueryUseCase useCase;
	private final EventEntryViewMapper mapper;

	public OutageController(final OutageEventQueryUseCase useCase) {
		this.useCase = useCase;
		this.mapper = EventEntryViewMapper.INSTANCE;
	}

	@GetMapping
	public PagingResponseEnvelopView<EventEntryView> index(@RequestParam(defaultValue = "1") final int page,
														   @RequestParam(value = "page-size", defaultValue = "5") final int size) {

		final PageResponse<List<OutageEventEntry>> response = useCase.execute(PageableRequest.of(page, size));

		return PagingResponseEnvelopView.of(mapper.from(response.getContent())
				, response.getTotalPages()
				, response.getTotalRecords());
	}

}
