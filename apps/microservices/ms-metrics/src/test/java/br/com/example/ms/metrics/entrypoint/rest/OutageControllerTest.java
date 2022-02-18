package br.com.example.ms.metrics.entrypoint.rest;

import br.com.example.ms.metrics.core.regulatory.outages.OutageEventEntry;
import br.com.example.ms.metrics.core.regulatory.outages.OutageEventQueryUseCase;
import br.com.example.ms.metrics.core.regulatory.outages.PageResponse;
import br.com.example.ms.metrics.core.regulatory.outages.PageableRequest;
import br.com.example.ms.metrics.templates.OutageEventEntryTemplate;
import br.com.example.ms.metrics.test.Category;
import br.com.six2six.fixturefactory.Fixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@WebMvcTest(value = OutageController.class)
@Tag(Category.UNIT_TEST)
public class OutageControllerTest {

	public static final int TOTAL_PAGES_ONE = 1;
	public static final int TOTAL_RECORDS_ONE = 1;
	public static final String QUERY_PARAM_PAGE = "page";
	public static final String QUERY_PARAM_PAGE_SIZE = "page-size";

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	OutageEventQueryUseCase useCase;

	@Captor
	ArgumentCaptor<PageableRequest> pagingRequestCaptor;

	@Test
	@DisplayName("Should return status OK and entries when exists data")
	void Should_returnStatusOKAndEntries_When_ExistsData() throws Exception {

		//Arrange
		final var fixture = (OutageEventEntry) Fixture.from(OutageEventEntry.class)
				.gimme(OutageEventEntryTemplate.BASIC);

		when(useCase.execute(Mockito.any(PageableRequest.class)))
				.thenReturn(PageResponse.of(singletonList(fixture), TOTAL_PAGES_ONE, TOTAL_RECORDS_ONE));

		//Act | Asserts
		mockMvc.perform(get("/discovery/v1/outages")
						.queryParam(QUERY_PARAM_PAGE, "1")
						.queryParam(QUERY_PARAM_PAGE_SIZE, "10"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data", hasSize(greaterThan(0))))
				.andExpect(jsonPath("$.meta.totalRecords", greaterThan(0)))
				.andExpect(jsonPath("$.meta.totalPages", greaterThan(0)));

		verify(useCase).execute(pagingRequestCaptor.capture());

		final PageableRequest captured = pagingRequestCaptor.getValue();

		assertThat(captured, notNullValue());
		assertThat(captured.getPage(), is(1));
		assertThat(captured.getSize(), is(10));

	}


}
