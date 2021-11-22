package br.com.example.ms.metrics.dataprovider.regulatory.outages;

import br.com.example.ms.metrics.core.regulatory.outages.PageableRequest;
import br.com.example.ms.metrics.templates.BasePackage;
import br.com.example.ms.metrics.templates.OutageEventTableTemplate;
import br.com.example.ms.metrics.test.Category;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag(Category.UNIT_TEST)
public class OutageEventGatewayImplTest {

	OutageEventGatewayImpl sut;

	@Mock
	OutageRepository repository;

	@BeforeAll
	public static void beforeAll() {
		FixtureFactoryLoader.loadTemplates(BasePackage.class.getPackageName());
	}

	@BeforeEach
	void beforeEach() {
		sut = new OutageEventGatewayImpl(repository);
	}

	@Test
	@DisplayName("Should return a paging result when there are existent events")
	void Should_ReturnPagingResult_When_ThereAreExistingEvents() {
		final var pageableRequest = PageableRequest.of(1, 10);

		final var events = Fixture.from(OutageEventTable.class).gimme(10, OutageEventTableTemplate.BASIC);
		final var page = Mockito.mock(Page.class);

		when(page.getContent()).thenReturn(events);
		when(page.getTotalPages()).thenReturn(2);
		when(page.getTotalElements()).thenReturn(15L);

		when(repository.findAll(eq(PageRequest.of(pageableRequest.getPage()
				, pageableRequest.getSize())))).thenReturn(page);

		final var result = sut.findAll(pageableRequest);

		assertThat(result, notNullValue());
		assertThat(result.getContent(), hasSize(greaterThan(0)));
	}

}
