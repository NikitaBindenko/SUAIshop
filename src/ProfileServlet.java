import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String uri = request.getRequestURI();
        
        if (uri.equals("/SUAIshop/profile") ) {
        	request.getRequestDispatcher("front/src/profile/profile1.html").include(request, response);
        	showOrders(user, out);
        	request.getRequestDispatcher("front/src/profile/profile2.html").include(request, response);
        	out.close();   
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String uri = request.getRequestURI();
        
        if (uri.equals("/SUAIshop/changePassword") ) {
        	String oldPassword = request.getParameter("Old Password");
        	if (oldPassword.equals(user.getPassword())){
        		Database db = new Database();
        		db.removeUser(db.getUser(user.getId()));
        		db.exportUsers();
        		user.setPassword(request.getParameter("New Password"));
        		db.addUser(user);
        		db.exportUsers();
        		request.getRequestDispatcher("front/src/profile/profile1.html").include(request, response);
        		out.println("Your password successfully changed");
        		request.getRequestDispatcher("front/src/profile/profile2.html").include(request, response);
        	}
        	else{
        		out.println("<!DOCTYPE html><html><body>" + "Please, try again..." + "</body</html>");
        	}
        }
    }
    
    public void confirmReceipt(int orderID){		//проверить
    	Catalog catalog = new Catalog();
    	OrderList orderlist = new OrderList(catalog);
    	Order oldOrder = orderlist.getOrder(orderID);
    	if(oldOrder.getStatus() == 1){
    		oldOrder.updateStatus();
    	}
    	orderlist.exportOrders();
    }
    
    public void showOrders(User user, PrintWriter out){		//тоже проверить
    	Catalog catalog = new Catalog();
    	OrderList orderlist = new OrderList(catalog);
    	LinkedList<Order> ordersToShow = orderlist.getOrders(user.getId());
    	for(int i = 0; i < ordersToShow.size(); i++){
    		out.println("<div><i id=\"inf_txt\">" + "\t" + ordersToShow.get(i).getId() + "\t" + ordersToShow.get(i).getPrice() + "\t" + ordersToShow.get(i).getAddress() + "</i></div>");
    	}
    }
}
