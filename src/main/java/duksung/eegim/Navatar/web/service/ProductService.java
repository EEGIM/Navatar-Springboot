package duksung.eegim.Navatar.web.service;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.Product.ProductDetail;
import duksung.eegim.Navatar.domain.Product.ProductSize;
import duksung.eegim.Navatar.domain.repository.ProductDetailRepository;
import duksung.eegim.Navatar.domain.repository.ProductRepository;
import duksung.eegim.Navatar.domain.repository.ProductSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductSizeRepository productSizeRepository;

    HashMap<String, String> brand_code = new HashMap<String, String>(){{
        put("apoc", "AP");
        put("lee", "LE");
        put("romanticcrown", "RO");
        put("mardimercredi", "MA");
        put("luvistrue", "LU");
    }};

    @Transactional(readOnly = true)
    public List<Product> getList(){
        return (List<Product>) productRepository.findAll();

    }
    @Transactional
    public Product getProduct(Long productNo){
        return productRepository.findByProductNo(productNo);
    }

    @Transactional
    public List<ProductDetail> getProductDetail(Long productNo){
        return (List<ProductDetail>) productDetailRepository.findByProductNoLike(productNo);
    }

    @Transactional
    public List<Product> getListByBrandNCate(String brand, String cate){
        System.out.println(brand);
        String code = brand_code.get(brand);
        System.out.println(code);
        return (List<Product>) productRepository.findByCategoryCodeLike(code+cate+"%");
    }

    @Transactional
    public List<ProductSize> getSize(Long productNo){
        return productSizeRepository.findByProductNo(productNo);
    }

    @Transactional
    public List<Product> getListByBrand(String brand){
        System.out.println(brand);
        String code = brand_code.get(brand);
        System.out.println(code);
        return (List<Product>) productRepository.findByCategoryCodeLike("%"+code+"%");
    }

}
