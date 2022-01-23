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
        String goodsID = request.getParameter("id");		//получаем id товара
        String action = request.getParameter("act");		//определяем необходимое действие 1-добавить 0-удалить
        if(action.equals("1")){
        	Goods addedGoods = catalog.getGoods(Integer.parseInt(goodsID));	//поиск товара в каталоге по id
        	user.addToBin(addedGoods);
        }
        else if(action.equals("0")){
        	Goods deletedGoods = catalog.getGoods(Integer.parseInt(goodsID));
        	user.deleteFromBin(deletedGoods);
        }
        PrintWriter out = response.getWriter();
	out.println("<!DOCTYPE html><html><body>");
	LinkedList<Goods> cart = user.getBin().getBin();
	for(int num = 0; num < cart.size(); num++){
		out.println(cart.get(num).getImageName() + "\n");
	}
	out.println("</body</html>");
        out.close();
    }

}
