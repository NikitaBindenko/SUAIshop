import java.util.*;

public class Bin {
    LinkedList<Goods> bin;

    public Bin(){
        bin = new LinkedList<>();
    }

    public void add(Goods goods){
        bin.add(goods);
    }

    public void delete(Goods goods){
        bin.remove(goods);
    }

    public LinkedList<Goods> getBin(){
        return new LinkedList(bin); //ТАК НАДО
    }

    public void clear(){
        bin.clear();
    }

    int commonPrice(){
        int commonPrice = 0;
        for(Goods good : bin ){
            commonPrice += good.getPrice();
        }
        return commonPrice;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Goods goods : bin){
            sb.append(goods.getId() + "\n" + goods.getImageName() + "\n" + goods.getPrice() + "\n");
        }
        return sb.toString();
    }
}
