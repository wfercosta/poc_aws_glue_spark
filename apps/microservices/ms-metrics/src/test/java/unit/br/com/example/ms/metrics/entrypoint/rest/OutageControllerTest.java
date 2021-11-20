package unit.br.com.example.ms.metrics.entrypoint.rest;

import br.com.example.ms.metrics.entrypoint.rest.OutageController;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Category;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith({SpringExtension.class})
@WebMvcTest(value = OutageController.class)
@Tag(Category.UNIT_TEST)
public class OutageControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@BeforeAll
	static void beforeAll() {

	}

	@Test
	@DisplayName("Test name")
	void Should() {
		Assertions.fail();
	}

}
