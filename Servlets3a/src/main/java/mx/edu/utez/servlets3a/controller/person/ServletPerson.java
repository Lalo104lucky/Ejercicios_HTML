package mx.edu.utez.servlets3a.controller.person;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import mx.edu.utez.servlets3a.model.person.BeanPerson;
import mx.edu.utez.servlets3a.model.person.DaoPerson;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "servletPerson", urlPatterns = {
        "/getPersons",
        "/createPersons",
        "/savePersons",
        "/deletePersons",
        "/updatePersons"
}
)
@MultipartConfig(maxFileSize = 1024*1024*100)

public class ServletPerson extends HttpServlet {
    private String action;
    private String redirect = "/getPersons";
    private BeanPerson person;
    private String name, lastname, age, email, phone, birthday, username, password, role;
    private Part image;

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
                break;
            default:
                redirect = "/getPersons";
        }
        request.getRequestDispatcher(redirect).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        action = request.getServletPath();

        switch (action){
            case "/savePersons":
                name = request.getParameter("name");
                lastname = request.getParameter("lastname");
                age = request.getParameter("age");
                email = request.getParameter("email");
                phone = request.getParameter("phone");
                birthday = request.getParameter("birthday");
                image = request.getPart("image");
                InputStream ImageBytes = image.getInputStream();

                username = request.getParameter("username");
                password = request.getParameter("password");
                role = request.getParameter("role");

                try {
                    person = new BeanPerson();
                    person.setName(name);
                    person.setLastname(lastname);
                    person.setAge(Integer.parseInt(age));
                    person.setEmail(email);
                    person.setPhone(phone);
                    Date birthdaySDF = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                    person.setBirthday(birthdaySDF);

                    person.setUsername(username);
                    person.setPassword(password);

                    boolean result = new DaoPerson().savePerson(person, ImageBytes);
                    if (result){
                        redirect = "/getPersons?result="+true+"&message="+
                                URLEncoder.encode("Persona registrada correctamente", StandardCharsets.UTF_8);
                    }else {
                        redirect = "/getPersons?result="+false+"&message="+
                                URLEncoder.encode("¡Error al Registrar Persona!", StandardCharsets.UTF_8);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            default:
                redirect = "/getPersons";
        }
    }
}
