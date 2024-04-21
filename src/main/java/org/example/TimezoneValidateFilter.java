package org.example;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;

@WebFilter(value = "/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse resp,
                            FilterChain chain) throws IOException,
            ServletException {

        String timezone = req.getQueryString();
        if (timezone != null && timezone.contains("=")) {
            try {
                ZoneId zoneId = ZoneId.of(timezone.substring(timezone.indexOf("=") + 1));
                chain.doFilter(req, resp);
            } catch (DateTimeException e) {
                resp.setStatus(400);
                resp.setContentType("text/html");
                resp.getWriter().write("Invalid timezone");
                resp.getWriter().close();
            }
        } else {
            resp.setStatus(400);
            resp.setContentType("text/html");
            resp.getWriter().write("Timezone parameter is missing or invalid");
            resp.getWriter().close();
        }
    }
}
