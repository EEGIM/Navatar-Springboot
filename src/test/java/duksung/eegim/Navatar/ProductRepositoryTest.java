package duksung.eegim.Navatar;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void 상품_불러오기(){

        Long productNo = Long.valueOf(1);
        Product product = productRepository.findByProductNo(productNo);
        System.out.println(product.getProductNo());
        assertThat(product.getProductNo()).isEqualTo(productNo);
    }

}
