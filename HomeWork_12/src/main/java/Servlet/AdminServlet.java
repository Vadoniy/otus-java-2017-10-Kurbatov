package Servlet;

import Cache.CacheEngine;
import DataSet.DataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static Servlet.Constants.*;

public class AdminServlet extends HttpServlet {

    private CacheEngine<Long, DataSet> cacheEngine;

    public AdminServlet(CacheEngine<Long, DataSet> cacheEngine){
        this.cacheEngine = cacheEngine;
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