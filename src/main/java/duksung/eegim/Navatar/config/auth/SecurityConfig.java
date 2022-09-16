package duksung.eegim.Navatar.config.auth;

import duksung.eegim.Navatar.config.auth.dto.AjaxAuthenticationEntryPoint;
import duksung.eegim.Navatar.domain.User.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/img/**", "/jsapp/**", "/html/**", "/products/{productNo}", "/brands/**").permitAll() // antMatchers() - 권한 관리 대상 지정 옵션 - 쇼핑몰 상품 조회로 바꾸기..
                .antMatchers("/users/signup", "users/signin").hasRole(Role.GUEST.name())
                .antMatchers("/products/{productNo}/cart", "/products/{productNo}/like", "/users/**").hasRole(Role.USER.name())// 이렇게 하면, 로그인 해야 다른 메뉴들 사용 가능, 그리고 user 관련 창은 사용 불가 위에 permitall로 권한 옮겨 보기
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/") // 로그아웃 성공 시 홈으로 이동
                .and()
                .oauth2Login()
                .loginPage("/users/signin")
                .permitAll()
                .defaultSuccessUrl("/users/signin") // 로그인 후 로드할 페이지
                .successHandler(successHandler())
                .userInfoEndpoint()
                .userService(customOAuth2UserService); // 소셜 로그인 성공 후 추가 정보 기입
        http.exceptionHandling().authenticationEntryPoint(new AjaxAuthenticationEntryPoint("/users/signin"));

    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new LoginSuccessHandler();
    }
}
