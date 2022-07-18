package duksung.eegim.Navatar.web.service;

import duksung.eegim.Navatar.domain.Product.User;
import duksung.eegim.Navatar.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User userSave(User user) {
        return userRepository.save(user);
    }

    public User getUser(long userno){
        return userRepository.findById(userno).orElse(null);
    }

    public void deleteUser(long userno){
        userRepository.deleteById(userno);
    }

}
