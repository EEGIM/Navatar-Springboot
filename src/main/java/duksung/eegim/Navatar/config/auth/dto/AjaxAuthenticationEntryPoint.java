package duksung.eegim.Navatar.config.auth.dto;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public AjaxAuthenticationEntryPoint(String loginUrl){
        super(loginUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String ajaxHeader = request.getHeader("X-Requested-With");

        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);

        if(isAjax){
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "세션 만료로 인해서 거부되었습니다.");
        } else {
            super.commence(request, response, authException);
        }
    }
}
