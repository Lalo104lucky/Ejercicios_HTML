package mx.edu.utez.servlets3a.controller.person;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "servletPerson", urlPatterns = {
        "/getPersons",
        "/createPersons",
        "/deletePersons",
        "/updatePersons"
}
)
public class ServletPerson extends HttpServlet {
    private String action;
    private String redirect = "/getPersons";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        action = request.getServletPath();

        switch (action){
            case "/getPersons":
                redirect = "/view/person/indexPerson.jsp";
            break;
            default:
                redirect = "/getPersons";
        }
        request.getRequestDispatcher(redirect).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}
