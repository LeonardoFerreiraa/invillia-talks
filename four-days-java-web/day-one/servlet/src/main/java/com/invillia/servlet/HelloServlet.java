package com.invillia.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/", loadOnStartup = 1)
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("Hello world");
    }

}


/*

Throwable
    -> Exception
        -> IOException
        -> RuntimeException
            -> NullpointerException
            -> IllegalArgumentException
    -> Error
        -> OutOfMemoryError
        -> ...


class Principal {


    public void bla() {
        try {
            throw new IOException();
        } catch (IOException e) {

        }
    }

    public void bla2() throws IOException {
        throw new IOException();
    }

}

 */
