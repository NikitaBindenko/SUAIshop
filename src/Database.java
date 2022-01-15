import java.io.*;
import java.util.*;

public class Database {

    LinkedList<User> db;
    File userList = new File("/home/user/tomcat9/apache-tomcat-9.0.54/webapps/SUAIshop/databases/list.txt");	//с полным путем работает, с относительным чет не получается

    public Database(){
        db = new LinkedList<>();
        db = importUsers();

    }

    public synchronized LinkedList<User> importUsers() { //записывает базу из файла в оперативную память
        LinkedList<User> users = new LinkedList<>();
        try {
            String in;
            Scanner sc = new Scanner(userList);
            while(sc.hasNextLine()) {
                in = sc.nextLine();
                String[] userData = in.split(" ");
                User newUser = new User(Integer.parseInt(userData[0]), userData[1], userData[2]);
                users.add(newUser);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }

    public synchronized void exportUsers() {    //записывает текущую базу в памяти в файл
        StringBuilder sb = new StringBuilder();
        for(int userNumber = 0; userNumber < db.size(); userNumber++){
            sb.append(db.get(userNumber).getId() + " " + db.get(userNumber).getLogin() + " " + db.get(userNumber).getPassword() + "\n");
        }
        try {
            FileWriter writer = new FileWriter(userList);
            writer.write(sb.toString());
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public synchronized void addUser(User newUser){ //добавляет пользователя в базу зарегестрированных в оперативной памяти
        db.add(newUser);
    }

    public synchronized void removeUser(User oldUser){   //удаляет пользователя из базы зарегестрированных в оперативной памяти
        if(oldUser.getId() != 0){
            db.remove(oldUser);
        }
    }

    public synchronized User getUser(int id){    //возвращает пользователя с указанным id
        User neededUser = new User(999,"","");
        for(User user : db){
            if(user.getId() == id) neededUser = user;
        }
        return neededUser;
    }

    public synchronized User getUser(String login){ //возвращает пользователя с указанным логином
        User neededUser = new User(999,"","");
        for(User user : db){
            if(user.getLogin().equals(login)) neededUser = user;
        }
        return neededUser;
    }
}
