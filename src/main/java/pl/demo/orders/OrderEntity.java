package pl.demo.orders;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.demo.generated.model.OrderStatusDto;

@Data
@Builder
@Entity
@Table(name = "orders")
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(value = EnumType.STRING)
    private OrderStatusDto status;

}
