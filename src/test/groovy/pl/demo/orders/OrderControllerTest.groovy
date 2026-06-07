package pl.demo.orders

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import pl.demo.configuration.ControllerTest
import pl.demo.generated.model.OrderDto
import pl.demo.generated.model.OrderStatusDto

class OrderControllerTest extends ControllerTest {

	private static final String ORDERS_URL = "/v1/orders"

	@Autowired
	private JdbcTemplate jdbcTemplate

	def "should return all orders"() {
		given:
			jdbcTemplate.execute("INSERT INTO orders (name, status) VALUES ('Order 1', 'PLACED')")
			jdbcTemplate.execute("INSERT INTO orders (name, status) VALUES ('Order 2', 'COMPLETED')")

		when:
			def response = restTemplate.getForEntity(ORDERS_URL, String)

		then:
			response.statusCode.value() == 200
		and:
			jsonEquals(response.body, [
					new OrderDto().id(1L).name("Order 1").status(OrderStatusDto.PLACED),
					new OrderDto().id(2L).name("Order 2").status(OrderStatusDto.COMPLETED)
			])
	}

}
