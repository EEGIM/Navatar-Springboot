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

        User user = userRepository.findByEmail(email)
                        .map(entity -> entity.update(registerDto.getWeight(), registerDto.getHeight()))
                                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다." + email));

        System.out.println("몸무게:" +registerDto.getWeight()+ " 키:"+ registerDto.getHeight());
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
        for (Product p : products){
            price += p.getNormalPrice();
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
    public List<Product> getSizeRecommand(String email) {
        HashMap<String, Long> info = getSizeInfo(email);
        List<Long> products = satisfactionRepository.getProductNoList(info.get("height"), info.get("weight"));
        List<Product> productList = new ArrayList<Product>();
        for (Long p : products){ // 이부분 다시 해보기 + 느림..
            productList.add(productRepository.findByProductNo(p));
        }
        return productList;
    }


}
