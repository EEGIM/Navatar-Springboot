package duksung.eegim.Navatar.web.service;

import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.User.*;
import duksung.eegim.Navatar.domain.repository.*;
import duksung.eegim.Navatar.web.dto.CartDto;
import duksung.eegim.Navatar.web.dto.LikeDto;
import duksung.eegim.Navatar.web.dto.ReviewDto;
import duksung.eegim.Navatar.web.dto.UserRegisterDto;
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
    private LikeRepository likeRepository;
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private ReviewRepository reviewRepository;
    private SatisfactionRepository satisfactionRepository;

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Long userSave(UserRegisterDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getUserno();
    }

    @Transactional // 표기 필수! 잊지마,,
    public String userUpdate(String email, UserRegisterDto registerDto) {

        User user = userRepository.findByEmail(email)
                        .map(entity -> entity.update(registerDto.getWeight(), registerDto.getHeight()))
                                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다." + email));

        System.out.println("몸무게:" +registerDto.getWeight()+ " 키:"+ registerDto.getHeight());
//        user.update(registerDto.getWeight(), registerDto.getHeight());
        return email;

    }

    public User getUser(long userno){
        return userRepository.findById(userno).orElse(null);
    }

    public User getUser(String email){
        Optional<User> user = userRepository.findByEmail(email); // 코드 개선
        return user.get();
    }

    public void deleteUser(long userno){
        userRepository.deleteById(userno);
    }

    @Transactional
    public void addLike(Long productNo, String email){

        LikeDto likeDto = LikeDto.builder()
                .productNo(productRepository.findByProductNo(productNo))
                .userNo(getUser(email).getUserno())
                .build();
        likeRepository.save(likeDto.toEntity());
    }

    @Transactional
    public List<Product> getLikeList(String email){ // 코드 예쁘게 수정하기...
        List<Like> likes = likeRepository.findByUserNo(getUser(email).getUserno());
        List<Product> products = new ArrayList<Product>();
        for (Like l : likes){
            products.add(l.getProductNo());
        }
        return products;
    }

    @Transactional
    public void addCart(CartDto requestDto, String email, Long productNo){
        CartDto cartDto = requestDto;
        cartDto.setUserNo(getUser(email).getUserno());
        cartDto.setProduct(productRepository.findByProductNo(productNo));

        cartRepository.save(cartDto.toEntity());
    }

    @Transactional
    public List<Cart> getCart(String email){
        List<Cart> cartList = cartRepository.findByUserNo(getUser(email).getUserno());
        return cartList;
    }

    @Transactional
    public void deleteFromCart(Long cartNo){
        cartRepository.deleteById(cartNo);
    }

    @Transactional
    public Review review(Long cartNo){
        return reviewRepository.findByCartNo(cartNo).map(entity-> entity).orElse(null);
    }

    @Transactional
    public void writeReview(ReviewDto requestDto, Long cartNo, Long productNo){

        Review review = reviewRepository.findByCartNo(cartNo)
                .map(entity -> entity.update(requestDto.getRating(), requestDto.getContent()))
                .orElseGet(()-> {
                    ReviewDto reviewDto = new ReviewDto(requestDto, cartNo, productNo);
                    return reviewRepository.save(reviewDto.toEntity());
                });
    }

    @Transactional // 이거는 진짜 바꾸기 (가격 계산 위한 임시 함수)
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
