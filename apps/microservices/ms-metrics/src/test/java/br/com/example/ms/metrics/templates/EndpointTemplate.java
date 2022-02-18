package br.com.example.ms.metrics.templates;

import br.com.example.ms.metrics.core.regulatory.outages.Endpoint;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class EndpointTemplate implements TemplateLoader {
	public static final String BASIC = "BASIC";

	@Override
	public void load() {
		Fixture.of(Endpoint.class).addTemplate(BASIC, new Rule() {{
			add("url", "https://api.banco.com.br/open-banking/discovery/v1/outages");
		}});
	}
}
