package pl.demo.configuration

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContainerizedTest extends Specification {

	@LocalServerPort
	protected int port

	@DynamicPropertySource
	static void registerPgProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgresContainer.&getJdbcUrl)
		registry.add("spring.datasource.username", postgresContainer.&getUsername)
		registry.add("spring.datasource.password", postgresContainer.&getPassword)
	}

	static def postgresContainer = new PostgreSQLContainer<>("postgres:16-alpine")

	def setupSpec() {
		postgresContainer.start()
	}

}
