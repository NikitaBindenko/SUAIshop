package Controller;

import Model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        PrintWriter out = response.getWriter();
        
        if(name.equals("") || password.equals("")){out.println("<!DOCTYPE html><html><head><link rel=\"stylesheet\" href=\"front/src/service_style.css\"></head><body>" + "<div id=\"words\" >Please, try again...</div>" + "</body</html>");}	//check if login and password were typed
        else{
        Database userlist = new Database();
        User loggingUser = userlist.getUser(name);
        
        if(loggingUser.getId() != 999){
        	out.println("<!DOCTYPE html><html><head><link rel=\"stylesheet\" href=\"front/src/service_style.css\"></head><body>" + "<div id=\"words\" >User " + loggingUser.getLogin() +" already exists!</div>" + "</body</html>");
        }	//refuse registration - user was found in userlist
        else{
        	loggingUser.setLogin(name);
        	loggingUser.setPassword(password);
        	loggingUser.setId((int)(System.currentTimeMillis() % 990) + 1);	//УБРАТЬ РАНДОМ
        	userlist.addUser(loggingUser);
        	userlist.exportUsers();
        	HttpSession session = request.getSession();
        	session.setAttribute("user", loggingUser);
        	//out.println("<!DOCTYPE html><html><body>" + "Вы успешно зарегестрированы!" + "</body</html>");
        	response.sendRedirect(request.getContextPath() + "/profile");
        }
        out.close();
    }
  }
}
