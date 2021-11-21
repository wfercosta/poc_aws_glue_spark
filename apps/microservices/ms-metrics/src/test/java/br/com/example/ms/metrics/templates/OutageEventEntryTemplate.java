package br.com.example.ms.metrics.templates;

import br.com.example.ms.metrics.core.regulatory.outages.Endpoint;
import br.com.example.ms.metrics.core.regulatory.outages.OutageEventEntry;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class OutageEventEntryTemplate implements TemplateLoader {

	public static final String BASIC = "BASIC";

	@Override
	public void load() {

		Fixture.of(OutageEventEntry.class).addTemplate(BASIC, new Rule() {{
			add("occurrenceDate", LocalDateTime.now());
			add("duration", Duration.of(10, ChronoUnit.MINUTES));
			add("partial", Boolean.TRUE);
			add("explanation", "API Gateway Updates");
			add("endpoints", Fixture.from(Endpoint.class).gimme(2, EndpointTemplate.BASIC));
		}});

	}
}
