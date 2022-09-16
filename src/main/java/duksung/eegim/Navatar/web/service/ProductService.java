package duksung.eegim.Navatar.web.service;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.Product.ProductDetail;
import duksung.eegim.Navatar.domain.Product.ProductSize;
import duksung.eegim.Navatar.domain.Review.Review;
import duksung.eegim.Navatar.domain.Product.ProductDetailRepository;
import duksung.eegim.Navatar.domain.Product.ProductRepository;
import duksung.eegim.Navatar.domain.Product.ProductSizeRepository;
import duksung.eegim.Navatar.domain.Review.ReviewRepository;
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

    @Autowired
    private ReviewRepository reviewRepository;

    HashMap<String, String> brand_code = new HashMap<String, String>(){{
        put("apoc", "AP");
        put("lee", "LE");
        put("romanticcrown", "RO");
        put("mahagrid", "MA");
        put("luvistrue", "LU");
    }};

    @Transactional(readOnly = true)
    public List<Product> getList(){
        return (List<Product>) productRepository.findAll();

    }

    @Transactional
    public List<Product> getList(List<Long> products){
        List<Product> productList = new ArrayList<Product>();
        for (Long p : products){ // 이부분 다시 해보기
            productList.add(productRepository.findByProductNo(p));
        }
        return productList;
    }

    @Transactional
    public Product getProduct(Long productNo){
        return productRepository.findByProductNo(productNo);
    }

    @Transactional
    public List<ProductDetail> getProductDetail(Long productNo){
        return (List<ProductDetail>) productDetailRepository.findByProductNo(productNo);
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

    @Transactional
    public List<Review> getReviews(Long productNo){
        return reviewRepository.findByProductNo(productNo);
    }
}
