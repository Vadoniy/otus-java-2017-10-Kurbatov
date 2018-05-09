package servlet;

import Cache.CacheEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static servlet.Constants.*;

public class AdminServlet extends HttpServlet {

    private CacheEngine cacheEngine;

    public AdminServlet(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        cacheEngine = (CacheEngine) context.getBean("cacheEngine");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> variables = new HashMap();
        String requestLogin = request.getParameter(LOGIN_PARAMETER_NAME);
        String requestPassword = request.getParameter(PASSWORD_PARAMETER_NAME);

        if (LOGIN.equals(requestLogin) && PASS.equals(requestPassword)) {
            variables.put("PORT", PORT);
            variables.put("Hits", cacheEngine.getHitCount());
            variables.put("Miss", cacheEngine.getMissCount());

            response.getWriter().println(Processor.instance().getPage(CACHE_PAGE_PARAMETER_NAME, variables));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().println(Processor.instance().getPage(LOGIN_PAGE_PARAMETER_NAME, variables));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}