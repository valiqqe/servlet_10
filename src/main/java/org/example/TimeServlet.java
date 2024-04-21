package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException,
            IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<html><body>");

        String parameter = "UTC";
        if(req.getQueryString() != null) {
            parameter = req.getQueryString();
        }

        out.write("<p>${dateTime}</p>"
                .replace("${dateTime}",
                        ZonedDateTime.now(ZoneId.of(
                                        parameter.substring(
                                                parameter.indexOf("=")+1)))
                                .format(DateTimeFormatter.ofPattern(
                                        "dd-MM-yyyy HH:mm:ss v"))));

        out.print("</body></html>");
        out.close();
    }
}
