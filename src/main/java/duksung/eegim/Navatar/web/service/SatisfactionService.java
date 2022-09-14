package duksung.eegim.Navatar.web.service;

import duksung.eegim.Navatar.domain.User.Satisfaction;
import duksung.eegim.Navatar.domain.repository.SatisfactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class SatisfactionService {

    @Autowired
    private SatisfactionRepository satisfactionRepository;

    private final ProductService productService;
    private final UserService userService;

    @Transactional
    public void SuggestProduct(String email){
        // HashMap<String, Long> info = userService.getSizeInfo(email);
       // Long height = info.get("height");
        // Long weight = info.get("weight");

        //satisfactionRepository.getProductList(height, weight);
    }


}
