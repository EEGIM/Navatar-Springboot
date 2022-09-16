package duksung.eegim.Navatar.web.service;

import duksung.eegim.Navatar.domain.Cart.Cart;
import duksung.eegim.Navatar.domain.Cart.CartRepository;
import duksung.eegim.Navatar.domain.Product.ProductRepository;
import duksung.eegim.Navatar.web.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void addCart(CartDto requestDto, Long userNo, Long productNo){
        CartDto cartDto = requestDto;
        cartDto.setUserNo(userNo);
        cartDto.setProduct(productRepository.findByProductNo(productNo));

        cartRepository.save(cartDto.toEntity());
    }

    @Transactional
    public List<Cart> getCartByUserNo(Long userNo){
        List<Cart> cartList = cartRepository.findByUserNo(userNo);
        return cartList;
    }

    @Transactional
    public Cart getCartByCartNo(Long cartNo){
        return cartRepository.findById(cartNo).get();
    }

    @Transactional
    public void deleteFromCart(Long cartNo){
        cartRepository.deleteById(cartNo);
    }

}
