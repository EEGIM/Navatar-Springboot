package duksung.eegim.Navatar.config.auth;

import duksung.eegim.Navatar.config.auth.dto.SessionUser;
import duksung.eegim.Navatar.domain.User.Role;
import duksung.eegim.Navatar.domain.User.User;
import duksung.eegim.Navatar.domain.repository.UserRepository;
import duksung.eegim.Navatar.web.dto.UserRegisterDto;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Data
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, @NotNull Authentication authentication) throws IOException, ServletException {
        HttpSession httpSession = request.getSession();
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        Optional<User> user = userRepository.findByEmail(sessionUser.getEmail());

  // customoauth2userservice, oauthattributes 확인해서 지금 로그인 한 사용자의 정보를 얻어올 수 잇ㄸ로ㅗㄱ 하기.
        if (user.isPresent()){
            Role role = user.get().getRole();

            if (role.equals(Role.GUEST)) {
                redirectStrategy.sendRedirect(request, response, "/users/signup");
            }
            else if (role.equals(Role.USER)){
                redirectStrategy.sendRedirect(request, response, "/users/mypage");
            }
        }

    }
}
