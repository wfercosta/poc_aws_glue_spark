package br.com.example.ms.metrics.test;

import br.com.example.ms.metrics.templates.BasePackage;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

	@BeforeAll
	public static void beforeAll() {
		FixtureFactoryLoader.loadTemplates(BasePackage.class.getPackageName());
	}
}
