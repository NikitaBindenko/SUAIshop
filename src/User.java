
import java.util.*;

public class User {

    private int id;
    private String login;
    private String password;
    boolean isSeller;
    Bin bin;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        isSeller = false;
        bin = new Bin();
    }
    public User(String login, String password) {  //admin id = 0
        this.id = 0;
        this.login = login;
        this.password = password;
        isSeller = true;
        bin = new Bin();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    boolean isSeller(){
        return isSeller;
    }   //возвращает 1 если пользователь явлется продавцом(администратором)

    public void addToBin(Goods goods){ bin.add(goods); }    //добавляет товар в корзину

    public void deleteFromBin(Goods goods){ bin.delete(goods); }    //удаляет товар из корзины

    public Bin getBin(){ return bin; }      //возвращает корзину этого пользователя

    public LinkedList<Order> getOrders(OrderList orderList){ return orderList.getOrders(id); }  //возвращает список заказов для этого пользователя

    public Order createNewOrder(int orderId, String address){
        Order newOrder = new Order(orderId, id, 0, bin.commonPrice(), bin.getBin(), address);   //создает новый заказ из содержимого корзины и очищает ее
        bin.clear();
        return newOrder;
    }
}
