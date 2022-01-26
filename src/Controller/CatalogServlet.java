package Controller;

import Model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CatalogServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        response.setContentType("text/html");
        try{
        	HttpSession session = request.getSession(false);
        	User user = (User)session.getAttribute("user");
        	if (user != null){
        		request.getRequestDispatcher("front/src/Shopping/with_Cart.html").include(request, response);
        	}
        	else{
        		request.getRequestDispatcher("front/src/index.html").include(request, response);
        	}
        }catch(NullPointerException e){
        	request.getRequestDispatcher("front/src/index.html").include(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        response.setContentType("text/html");
        try{
        	HttpSession session = request.getSession(false);
        	User user = (User)session.getAttribute("user");
        	if (user != null){
        		request.getRequestDispatcher("front/src/Shopping/with_Cart.html").include(request, response);
        	}
        	else{
        		request.getRequestDispatcher("front/src/index.html").include(request, response);
        	}
        }catch(NullPointerException e){
        	request.getRequestDispatcher("front/src/index.html").include(request, response);
        }
    }
}
