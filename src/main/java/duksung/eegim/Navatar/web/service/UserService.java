package duksung.eegim.Navatar.web.service;

import duksung.eegim.Navatar.domain.User.User;
import duksung.eegim.Navatar.domain.repository.UserRepository;
import duksung.eegim.Navatar.web.dto.UserRegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User getUser(long userno){
        return userRepository.findById(userno).orElse(null);
    }

    public void deleteUser(long userno){
        userRepository.deleteById(userno);
    }

}
