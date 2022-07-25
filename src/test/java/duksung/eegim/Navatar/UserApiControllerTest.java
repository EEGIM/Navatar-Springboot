package duksung.eegim.Navatar;

import duksung.eegim.Navatar.domain.User.User;
import duksung.eegim.Navatar.domain.repository.UserRepository;
import duksung.eegim.Navatar.web.dto.UserRegisterDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 회원등록() throws Exception {
        //given
        String id = "abcd";
        String password = "abc123";
        String name = "name";
        String email = "123@aa.com";
        Long weight = 60L;
        Long height = 165L;
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .id(id)
                .password(password)
                .name(name)
                .email(email)
                .weight(weight)
                .height(height)
                .build();

        String url = "http://localhost:"+port+"/users/signup";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, userRegisterDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getId()).isEqualTo(id);
        assertThat(all.get(0).getName()).isEqualTo(name);
    }
}
