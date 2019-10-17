package com.invillia.jsp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name", "Rafael");

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher("first.jsp");
        requestDispatcher.forward(req, resp);
    }
}
