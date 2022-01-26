package Controller;

import Model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class BinServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");	//получаем текущего пользователя
        Catalog catalog = new Catalog();			//создаем каталог для поиска товара
        String address = request.getParameter("Adress");	//получаем адрес покупателя
        String goodsIDs = request.getParameter("products");	//получаем строку с параметрами (id заказанных товаров)
        String[] goodsID = goodsIDs.split(",");
        LinkedList<Goods> listOfGoods = new LinkedList<>();
        int commonPrice = 0;
        for(int num = 0; num < goodsID.length; num++){
        	Goods addedGoods = catalog.getGoods(Integer.parseInt(goodsID[num]));
        	listOfGoods.add(addedGoods);
        	commonPrice = commonPrice + addedGoods.getPrice();
        }
        Order order = new Order(generateID(), user.getId(), 0, commonPrice, listOfGoods, address);
        OrderList orderlist = new OrderList(catalog);
        orderlist.addOrder(order);
        orderlist.exportOrders();
        
        response.sendRedirect(request.getContextPath() + "/profile");
    }
    
    public int generateID(){
    	return (int)(Math.random() * 1000000 % 899998) + 100000;
    }

}
