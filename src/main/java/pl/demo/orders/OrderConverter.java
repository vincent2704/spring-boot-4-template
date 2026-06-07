package pl.demo.orders;

import lombok.experimental.UtilityClass;
import pl.demo.generated.model.OrderDto;

import java.util.List;

@UtilityClass
public class OrderConverter {

    public OrderDto toDto(OrderEntity orderEntity) {
        return new OrderDto()
                .id(orderEntity.getId())
                .name(orderEntity.getName())
                .status(orderEntity.getStatus());
    }

    public List<OrderDto> toDto(List<OrderEntity> orderEntities) {
        return orderEntities.stream()
                .map(OrderConverter::toDto)
                .toList();
    }
}
