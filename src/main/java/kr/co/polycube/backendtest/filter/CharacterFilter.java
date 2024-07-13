package kr.co.polycube.backendtest.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CharacterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String queryString = httpRequest.getQueryString();

        if(queryString != null && hasCharacters(queryString)){

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"reason\": \"사용 불가능한 특수문자가 포함되어 있습니다.\"}");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean hasCharacters(String query){
        return !query.matches("[a-zA-Z0-9?&=/:*]");    //조건에 명시된 특수문자만 포함
    }
}
