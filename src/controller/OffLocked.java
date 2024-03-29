package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * desbloque al usuario activo cuenta es llamada
 */
@WebServlet("/offLocked")
public class OffLocked extends HttpServlet {

    private static final long serialVersionUID = 1L;
    HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        session = request.getSession();
        //al cambiar este atributo el usuario ya no cuenta como bloqueado
        session.setAttribute("intento",0);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("OK");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request, response);
    }
}