package pl.demo.products;

import lombok.experimental.UtilityClass;
import pl.demo.generated.model.ProductDto;

import java.util.List;

@UtilityClass
public class ProductConverter {

    public ProductDto toDto(ProductEntity productEntity) {
        return new ProductDto()
                .id(productEntity.getId())
                .name(productEntity.getName());
    }

    public List<ProductDto> toDto(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(ProductConverter::toDto)
                .toList();
    }
}
