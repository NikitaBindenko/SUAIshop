import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        String username = user.getLogin();
        out.println("<!DOCTYPE html><html><body>" + "Salam aleikum " + username + "</body</html>");	//заглушка для html страниц
        
        out.close();
    }
    
    public void changePassword(User user, String newPassword){	//проверить
    	user.setPassword(newPassword);
    	Database db = new Database();
    	db.removeUser(user);
    	user.setPassword(newPassword);
    	db.addUser(user);
    	db.exportUsers();
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
    		out.println(ordersToShow.get(i));
    	}
    }
}
