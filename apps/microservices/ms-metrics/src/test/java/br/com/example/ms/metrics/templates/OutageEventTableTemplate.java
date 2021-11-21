package br.com.example.ms.metrics.templates;

import br.com.example.ms.metrics.dataprovider.regulatory.outages.EndpointTable;
import br.com.example.ms.metrics.dataprovider.regulatory.outages.OutageEventTable;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class OutageEventTableTemplate implements TemplateLoader {
	public static final String BASIC = "BASIC";

	@Override
	public void load() {
		Fixture.of(OutageEventTable.class).addTemplate(BASIC, new Rule() {{
			add("occurrenceDate", LocalDateTime.now());
			add("duration", Duration.of(10, ChronoUnit.MINUTES));
			add("partial", Boolean.TRUE);
			add("explanation", "API Gateway Updates");
			add("endpoints", Fixture.from(EndpointTable.class).gimme(2, EndpointTableTemplate.BASIC));
		}});
	}
}
