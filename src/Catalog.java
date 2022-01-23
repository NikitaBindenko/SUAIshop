import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class Catalog {

    LinkedList<Goods> db;
    File catalog = new File("/home/user/tomcat9/apache-tomcat-9.0.54/webapps/SUAIshop/databases/catalog.txt");

    Catalog(){
        db = new LinkedList<>();
        db = importGoods();
    }

    public synchronized LinkedList<Goods> importGoods() {    //импортирует базу товаров из файла в оперативную память
        LinkedList<Goods> goods = new LinkedList<>();
        try {
            String in;
            Scanner sc = new Scanner(catalog);
            while(sc.hasNextLine()) {
                Goods newGoods = new Goods();
                in = sc.nextLine(); //считал id
                newGoods.setId(Integer.parseInt(in));
                in = sc.nextLine(); //считал price
                newGoods.setPrice(Integer.parseInt(in));
                in = sc.nextLine(); //считал imageName
                newGoods.setImageName(in);
                in = sc.nextLine(); //считал description
                newGoods.setDescription(in);
                goods.add(newGoods);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return goods;
    }

    public synchronized void exportGoods() {    //записывает текущую базу в памяти в файл
        StringBuilder sb = new StringBuilder();
        for(Goods exported : db){
            sb.append(exported.getId() + "\n" + exported.getPrice() + "\n" + exported.getImageName() + "\n" + exported.getDescription() + "\n");
        }
        try {
            FileWriter writer = new FileWriter(catalog);
            writer.write(sb.toString());
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public synchronized void addGoods(Goods newGoods){
        db.add(newGoods);
    }

    public synchronized void removeGoods(Goods oldGoods){
        db.remove(oldGoods);
    }

    public synchronized Goods getGoods(int id){
        Goods neededGoods = new Goods();
        for(Goods goods : db){
            if(goods.getId() == id) neededGoods = goods;
        }
        return neededGoods;
    }
}
