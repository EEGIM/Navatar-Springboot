package duksung.eegim.Navatar.web.service;

import duksung.eegim.Navatar.domain.Like.Like;
import duksung.eegim.Navatar.domain.Like.LikeRepository;
import duksung.eegim.Navatar.domain.Product.Product;
import duksung.eegim.Navatar.domain.Product.ProductRepository;
import duksung.eegim.Navatar.domain.User.UserRepository;
import duksung.eegim.Navatar.web.dto.LikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikeRepository likeRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Product> getLikeList(Long userNo){
        List<Like> likes = likeRepository.findByUserNo(userNo);
        List<Product> products = new ArrayList<Product>();
        for (Like l : likes){
            products.add(l.getProductNo());
        }
        return products;
    }

    @Transactional
    public void addLike(Long productNo, Long userNo){

        LikeDto likeDto = LikeDto.builder()
                .productNo(productRepository.findByProductNo(productNo))
                .userNo(userNo)
                .build();
        likeRepository.save(likeDto.toEntity());
    }
}
