package br.com.example.ms.metrics.dataprovider.regulatory.outages;

import br.com.example.ms.metrics.test.Category;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@Sql(value = "/db/migrations/h2/data/load.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db/migrations/h2/data/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Tag(Category.INTEGRATION_TEST)
public class OutageRepositoryTest {

	@Autowired
	OutageRepository sut;
	
}
