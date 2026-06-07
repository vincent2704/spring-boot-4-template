package pl.demo.products

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import pl.demo.configuration.ControllerTest
import pl.demo.generated.model.ProductDto

class ProductControllerTest extends ControllerTest {

	private static final String PRODUCTS_URL = "/v1/products"

	@Autowired
	private JdbcTemplate jdbcTemplate

	def "should return all products"() {
		given:
			jdbcTemplate.execute("INSERT INTO products (name) VALUES ('Product 1')")
			jdbcTemplate.execute("INSERT INTO products (name) VALUES ('Product 2')")

		when:
			def response = restTemplate.getForEntity(PRODUCTS_URL, String)

		then:
			response.statusCode.value() == 200
		and:
			jsonEquals(response.body, [
					new ProductDto().id(1L).name("Product 1"),
					new ProductDto().id(2L).name("Product 2")
			])
	}

}
