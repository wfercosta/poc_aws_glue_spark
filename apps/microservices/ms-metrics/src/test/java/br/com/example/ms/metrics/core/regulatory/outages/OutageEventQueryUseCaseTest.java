package br.com.example.ms.metrics.core.regulatory.outages;

import br.com.example.ms.metrics.templates.OutageEventEntryTemplate;
import br.com.example.ms.metrics.test.BaseTest;
import br.com.example.ms.metrics.test.Category;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag(Category.UNIT_TEST)
public class OutageEventQueryUseCaseTest extends BaseTest {

	OutageEventQueryUseCase sut;

	@Mock
	OutageEventGateway gateway;

	@BeforeEach
	void beforeEach() {
		sut = new OutageEventQueryUseCase(gateway);
	}

	@Test
	@DisplayName("Should return results when requested page has at least one event")
	void Should_ReturnResults_When_RequestedHasAtLeastOneEvent() {
		final var pageableRequest = PageableRequest.of(1, 10);
		final var data = (OutageEventEntry) Fixture.from(OutageEventEntry.class).gimme(OutageEventEntryTemplate.BASIC);

		when(gateway.findAll(eq(pageableRequest))).thenReturn(PageResponse.of(singletonList(data), 1, 10));

		final var result = sut.execute(pageableRequest);

		assertThat(result, notNullValue());
		assertThat(result.getContent(), hasSize(greaterThan(0)));
	}

}
