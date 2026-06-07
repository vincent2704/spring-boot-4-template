package pl.demo.configuration

import org.springframework.boot.restclient.RestTemplateBuilder
import org.springframework.boot.resttestclient.TestRestTemplate
import tools.jackson.databind.ObjectMapper

class ControllerTest extends ContainerizedTest {

	protected TestRestTemplate restTemplate

	def setup() {
		restTemplate = new TestRestTemplate(
				new RestTemplateBuilder().rootUri("http://localhost:$port")
		)
	}

	protected static boolean jsonEquals(String json, Object object) {
		new ObjectMapper().writeValueAsString(object) == json
	}

}
