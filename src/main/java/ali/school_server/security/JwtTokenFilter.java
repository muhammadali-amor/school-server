package ali.school_server.security;

import ali.school_server.repository.AuthRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenProvider tokenService;
    @Autowired
    AuthRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var email = tokenService.validateToken(token);
            var user = userRepository.findByEmail(email);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            String tokenReal = authHeader.substring(7);
            if (authHeader.startsWith("Bearer ") && !tokenReal.isEmpty()) {
                return tokenReal;
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }
//    private String recoverToken(HttpServletRequest request) {
//        var authHeader = request.getHeader("Authorization");
//        if (authHeader == null)
//            return null;
//        return authHeader.replace("Bearer ", "");
//    }
}
