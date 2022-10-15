package duksung.eegim.Navatar.config.auth;

import duksung.eegim.Navatar.config.auth.dto.SessionUser;
import duksung.eegim.Navatar.domain.User.Role;
import duksung.eegim.Navatar.domain.User.User;
import duksung.eegim.Navatar.domain.User.UserRepository;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

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

        //clearSession(request);

        HttpSession httpSession = request.getSession();
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        Optional<User> user = userRepository.findByEmail(sessionUser.getEmail());

        if (user.isPresent() && user.get().getRole().equals(Role.GUEST)){
            redirectStrategy.sendRedirect(request, response, "/users/signup");
            return;
        }


        String prevPage = (String) request.getSession().getAttribute("prevPage");

        if (prevPage != null){
            request.getSession().removeAttribute("prevPage"); // 기존의 이전 페이지 제거
        }

        String url = "/users/mypage"; // 기본 url

        if (savedRequest != null){
            url = savedRequest.getRedirectUrl();
        }

        if (prevPage != null && !prevPage.equals("")) {
            url = prevPage;
        }

        redirectStrategy.sendRedirect(request, response, url);
    }

    protected  void clearSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
