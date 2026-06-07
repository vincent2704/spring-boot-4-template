package pl.demo.products;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.demo.generated.api.ProductApi;
import pl.demo.generated.model.ProductCreateRequestDto;
import pl.demo.generated.model.ProductCreateResponseDto;
import pl.demo.generated.model.ProductDto;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(
                productService.getAll());
    }

    @Override
    public ResponseEntity<ProductCreateResponseDto> createProduct(ProductCreateRequestDto productCreateRequestDto) {
        return ResponseEntity.internalServerError().build();
    }

}
