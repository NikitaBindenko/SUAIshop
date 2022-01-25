
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Order {    //заказ знает своего заказчка, заказчик для просмотра заказов проходит по всему списку заказов
    public static File orders = new File("/home/user/IdeaProjects/SUAIshop/orders.txt");

    int id;
    int userId;
    int status;
    int price;
    LinkedList<Goods> order;
    String address;

    public Order(){}

    public Order(int id, int user_id, int status, int price, LinkedList<Goods> order, String address){
        this.id = id;
        this.userId = user_id;
        this.status = status;
        this.price = price;
        this.address = address;
        this.order = order;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public LinkedList<Goods> getOrder() { return order; }

    public void setOrder(LinkedList<Goods> order) { this.order = order; }

    public int getStatus() { return status; } //0 - заказ создан, 1 - заказ передан в пути, 2 - заказ получен

    public void updateStatus(){ status++; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id + " " + userId + " " + status + " " + price + " ");
        for (Goods goods : order) {
            sb.append(goods.getId() + " ");
        }
        sb.append(address);
        return sb.toString();
    }
    public String toString(boolean html) {
    	StringBuilder sb = new StringBuilder();
    	if(html){
        	for (Goods goods : order) {
            		sb.append("id" + goods.getId() + " " + goods.getPrice() + "<br>");
        	}
        }
        return sb.toString();
    }
}
