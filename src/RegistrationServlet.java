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
        
        if(name == null || password == null){out.println("<!DOCTYPE html><html><body>" + "Please, try again..." + "</body</html>");}	//check if login and password were typed
        
        Database userlist = new Database();
        User loggingUser = userlist.getUser(name);
        
        if(loggingUser.getId() != 999){
        	out.println("<!DOCTYPE html><html><body>" + "Пользователь уже существует" + "</body</html>");
        }	//refuse registration - user was found in userlist
        else{
        	loggingUser.setLogin(name);
        	loggingUser.setPassword(password);
        	loggingUser.setId((int)(System.currentTimeMillis() % 990) + 1);	//УБРАТЬ РАНДОМ
        	userlist.addUser(loggingUser);
        	userlist.exportUsers();
        	out.println("<!DOCTYPE html><html><body>" + "Вы успешно зарегестрированы!" + "</body</html>");
        }
        
	request.getRequestDispatcher("front/src/profile/profile.html").include(request, response);
        out.close();
    }

}
