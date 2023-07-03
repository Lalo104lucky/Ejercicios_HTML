package mx.edu.utez.servlets3a.controller.person;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.servlets3a.model.person.BeanPerson;
import mx.edu.utez.servlets3a.model.person.DaoPerson;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

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
                List<BeanPerson> personList = DaoPerson.findPersons();
                request.setAttribute("personList", personList);
                redirect = "/view/person/indexPerson.jsp";
            break;
            case "/createPersons":
                redirect = "/view/person/createPerson.jsp";

            default:
                redirect = "/getPersons";
        }
        request.getRequestDispatcher(redirect).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}
