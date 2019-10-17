package com.invillia.jsp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name", req.getParameter("nome"));
        req.setAttribute("email", req.getParameter("email"));

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/sucesso.jsp");
        requestDispatcher.forward(req, resp);
    }
}
