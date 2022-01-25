import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
       
        String uri = request.getRequestURI();
        
        if(user.isSeller()){
        	if (uri.equals("/SUAIshop/admin") ) {
        	request.getRequestDispatcher("front/src/admin/admin_profile1.html").include(request, response);
        	showOrders(user, out);
        	request.getRequestDispatcher("front/src/admin/admin_profile2.html").include(request, response);  
        	}
        	if (uri.equals("/SUAIshop/pack") ) {
        		int orderID = Integer.parseInt(request.getParameter("orderID"));
        		Catalog catalog = new Catalog();
    			OrderList orderlist = new OrderList(catalog);
    			Order oldOrder = orderlist.getOrder(orderID);
    			out.println("\n" + oldOrder);
    			if(oldOrder.getStatus() == 0){
    			oldOrder.updateStatus();
    			}
    		orderlist.exportOrders();
        	response.sendRedirect(request.getContextPath() + "/admin");  
        	}
        }
        else{out.println("<!DOCTYPE html><html><body>" + "You don't have enough rights to access this page..." + "</body</html>");}
        
        out.close();
    }
    
    public void showOrders(User user, PrintWriter out){
    	Catalog catalog = new Catalog();
    	OrderList orderlist = new OrderList(catalog);
    	LinkedList<Order> ordersToShow = orderlist.getAllOrders();
    	for(int i = 0; i < ordersToShow.size(); i++){
    		String status = "";
    		if(ordersToShow.get(i).getStatus() == 0){status = "created";}
    		else if(ordersToShow.get(i).getStatus() == 1){status = "sent";}
    		else if(ordersToShow.get(i).getStatus() == 2){status = "delivered";}
    		out.println("<div class=\"goods_container_item\">");
    		out.println("<div class=\"item_number\">id " + ordersToShow.get(i).getId() + "</div>");
    		out.println("<div class=\"item_info\">");
    		out.println("<div class = \"info_pos\">User " + ordersToShow.get(i).getUserId() +"</div>");
    		out.println("<div class = \"info_pos\">" + ordersToShow.get(i).getPrice() +"</div>");
    		out.println("<div class = \"info_pos\">" + ordersToShow.get(i).getAddress() +"</div>");
    		out.println("<div class = \"info_pos\">Items:<br>" + ordersToShow.get(i).toString(true) + "</div>"); //alternative toString method is used to form the order
    		out.println("</div><div class=\"item_block\"><div>");
    		if(status == "created"){out.println("<form method=\"GET\" action = \"/SUAIshop/pack\"><input type=\"hidden\" name=\"orderID\" value=\"" + ordersToShow.get(i).getId() + "\"/><button class=\"item_btn\">pack</button></form>");}
    		out.println("</div><div class=\"item_status\">" + status + "</div></div></div>");
    	}
    }

}
