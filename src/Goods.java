
public class Goods {

    int id;
    int price;
    String imageName;
    String description;

    public Goods(){}

    public Goods(int id, int price, String imageName, String description){
        this.id = id;
        this.price = price;
        this.imageName = imageName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) { this.description = description; }

}
