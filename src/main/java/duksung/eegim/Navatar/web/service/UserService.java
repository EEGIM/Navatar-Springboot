package duksung.eegim.Navatar.web.service;

import duksung.eegim.Navatar.domain.User.User;
import duksung.eegim.Navatar.domain.repository.UserRepository;
import duksung.eegim.Navatar.web.dto.UserRegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

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
        user.update(registerDto.getWeight(), registerDto.getHeight());
        return email;

    }

    public User getUser(long userno){
        return userRepository.findById(userno).orElse(null);
    }

    public void deleteUser(long userno){
        userRepository.deleteById(userno);
    }

}
