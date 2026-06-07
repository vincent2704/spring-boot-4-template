package pl.demo.orders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.demo.generated.api.OrderApi;
import pl.demo.generated.model.OrderCreateRequestDto;
import pl.demo.generated.model.OrderCreateResponseDto;
import pl.demo.generated.model.OrderDto;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public ResponseEntity<List<OrderDto>> getOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @Override
    public ResponseEntity<OrderCreateResponseDto> createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        return ResponseEntity.internalServerError().build();
    }
}
