package br.com.example.ms.metrics.templates;

import br.com.example.ms.metrics.dataprovider.regulatory.outages.EndpointTable;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class EndpointTableTemplate implements TemplateLoader {
	public static final String BASIC = "BASIC";

	@Override
	public void load() {
		Fixture.of(EndpointTable.class).addTemplate(BASIC, new Rule() {{
			add("url", "https://api.banco.com.br/open-banking/discovery/v1/outages");
		}});
	}
}
