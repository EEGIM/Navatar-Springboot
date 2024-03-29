package duksung.eegim.Navatar.web.service;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.Product.ProductRepository;
import duksung.eegim.Navatar.domain.Satisfaction.SatisfactionRepository;
import duksung.eegim.Navatar.domain.User.*;
import duksung.eegim.Navatar.web.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    // 서비스도 전체 조회와 유저 개인별 조회 용도 별로 나누기

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private SatisfactionRepository satisfactionRepository;

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public String userUpdate(String email, UserRegisterDto registerDto) {

        Long weight = registerDto.getWeight() == null ? 0L : registerDto.getWeight();
        Long height = registerDto.getWeight() == null ? 0L : registerDto.getHeight();

        User user = userRepository.findByEmail(email)
                        .map(entity -> entity.update(weight, height))
                                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다." + email));

       return email;

    }

    public User getUser(long userno){
        return userRepository.findById(userno).orElse(null);
    }

    public User getUser(String email){
        Optional<User> user = userRepository.findByEmail(email); // 코드 개선
        return user.get();
    }

    public Long getUserNo(String email){
        return userRepository.findByEmail(email).get().getUserno();
    }

    @Transactional // (가격 계산 위한 임시 함수)
    public int getPrice(List<Product> products){
        int price = 0;
        for (Product product : products){
            price += product.getNormalPrice();
        }
        return price;
    }

    @Transactional
    public HashMap<String, Long> getSizeInfo(String email){
        User user = getUser(email);
        HashMap<String, Long> info = new HashMap<String, Long>();
        info.put("height", user.getHeight());
        info.put("weight", user.getWeight());
        return info;
    }

    @Transactional
    public List<Product> getSizeRecommand(HashMap<String, Long> info) {
        List<Long> products = satisfactionRepository.getProductNoList(info.get("height"), info.get("weight"));
        List<Product> productList = new ArrayList<Product>();
        for (Long product : products){ // 이부분 다시 해보기 + 느림..
            productList.add(productRepository.findByProductNo(product));
        }
        return productList;
    }

}
