
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class OrderList {
    LinkedList<Order> db;
    File directory = new File(System.getProperty("user.dir"));
    String dbFilesDirectory = directory.getParent();
    File orderList = new File(dbFilesDirectory + "/webapps/SUAIshop/databases/orders.txt");
    
    public OrderList(Catalog catalog){
        db = new LinkedList<>();
        db = importOrders(catalog);
    }

    public synchronized LinkedList<Order> importOrders(Catalog catalog) {    //каталог нужен для поиска товаров по id
        LinkedList<Order> orders = new LinkedList<>();
        try {
            String in;
            Scanner sc = new Scanner(orderList);
            while(sc.hasNextLine()) {
                in = sc.nextLine();
                String[] orderData = in.split(" ");
                LinkedList <Goods> currentGoods = new LinkedList<>();
                for(int wordNumber = 4; wordNumber < orderData.length - 1; wordNumber++){
                    currentGoods.add(catalog.getGoods(Integer.parseInt(orderData[wordNumber]))); //в каталоге ищу артикул и добавляю товар с таким артикулом в список товаров
                }
                Order newOrder = new Order(Integer.parseInt(orderData[0]),  Integer.parseInt(orderData[1]), Integer.parseInt(orderData[2]), Integer.parseInt(orderData[3]), currentGoods, orderData[orderData.length - 1]);
                orders.add(newOrder);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return orders;
    }

    public synchronized void exportOrders () {
        StringBuilder sb = new StringBuilder();
        for(Order exported : db){
            sb.append(exported.toString());
            sb.append("\n");
        }
        try {
            FileWriter writer = new FileWriter(orderList);
            writer.write(sb.toString());
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public synchronized void addOrder(Order newOrder){
        db.add(newOrder);
    }

    public synchronized void removeOrder(Order oldOrder){
        db.remove(oldOrder);
    }

    public synchronized LinkedList<Order> getOrders(int user_id){    //возвращает все заказы пользователя
        LinkedList<Order> orders = new LinkedList<>();
        for(Order currentOrder : db){
            if (currentOrder.getUserId() == user_id) orders.add(currentOrder);
        }
        return orders;
    }
    
    public synchronized LinkedList<Order> getAllOrders(){    //возвращает все заказы
        LinkedList<Order> orders = new LinkedList<>();
        for(Order currentOrder : db){
            orders.add(currentOrder);
        }
        return orders;
    }

    public synchronized Order getOrder(int id){  //возвращает заказ с указанным id
        Order neededOrder = new Order();
        for(Order currentOrder : db){
            if (currentOrder.getId() == id) neededOrder = currentOrder;
            break;
        }
        return neededOrder;
    }
}
