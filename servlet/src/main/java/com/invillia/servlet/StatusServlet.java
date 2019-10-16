package com.invillia.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatusServlet extends HttpServlet {

    @Override
    protected void doGet(
            final HttpServletRequest req,
            final HttpServletResponse resp
    ) throws ServletException, IOException {
        final String sc = req.getParameter("sc");

        if (sc == null) {
            resp.setStatus(200);
        } else {
            resp.setStatus(Integer.parseInt(sc));
        }
    }

    @Override
    protected void doPost(
            final HttpServletRequest req,
            final HttpServletResponse resp
    ) throws ServletException, IOException {
        final String sc = req.getParameter("sc");

        if (sc == null) {
            resp.setStatus(200);
        } else {
            resp.setStatus(Integer.parseInt(sc));
        }
    }
}
