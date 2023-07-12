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

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet( name = "servletperson", urlPatterns = {
        "/getPersons",
        "/createPerson",
        "/savePerson",
        "/deletePerson",
        "/updatePerson"
}
)
@MultipartConfig (maxFileSize = 1024 * 1024 * 100)
public class ServletPerson extends HttpServlet {
    private BeanPerson person;
    private String id, action, name, lastname, email, age, phone, birthday, username, password, role;
    private Part image;
    private String redirect = "/getPersons";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        action = request.getServletPath();
        switch (action){
            case "/getPersons":
                List<BeanPerson> personList = DaoPerson.findPersons();
                System.out.println(personList.size());
                request.setAttribute("personList", personList);
                redirect = "/view/person/indexPerson.jsp";
                break;
            case "/createPerson":
                redirect = "/view/person/createPerson.jsp";
                break;
            case "/updatePerson":
                id = request.getParameter("id");
                person = new DaoPerson().findPerson(id!=null ? Long.parseLong(id):0);
                if(person!=null){
                    request.setAttribute("person", person);
                }else {
                    redirect="/getPersons?result="+true+"&message="+
                            URLEncoder.encode("¡No se encontró recurso!", StandardCharsets.UTF_8);
                }

                redirect = "/view/person/updatePerson.jsp";
                break;
            default:
                redirect = "/getPersons";
                break;

        }
        request.getRequestDispatcher(redirect).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        action = request.getServletPath();
        switch(action){
            case "/savePerson":
                name = request.getParameter("name");
                lastname = request.getParameter("lastname");
                age = request.getParameter("age");
                email = request.getParameter("email");
                phone = request.getParameter("phone");
                image = request.getPart("image");
                InputStream imageBitys = image.getInputStream();
                birthday = request.getParameter("birthday");
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
                    person.setRole(role);

                    boolean result = new DaoPerson().savePerson(person, imageBitys);
                    if (result){
                        redirect="/getPersons?result="+true+"&message="+
                                URLEncoder.encode("Persona registrada correctamente", StandardCharsets.UTF_8);
                    }else{
                        URLEncoder.encode("Persona registrada correctamente", StandardCharsets.UTF_8);
                    }

                } catch (ParseException e){
                    e.printStackTrace();
                }

                break;
            default:
                redirect="/getPersons";

        }
        response.sendRedirect(request.getContextPath()+redirect);
    }
}