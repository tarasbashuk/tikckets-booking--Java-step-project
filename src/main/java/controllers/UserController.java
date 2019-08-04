package controllers;

import entities.Passenger;
import entities.User;
import services.UserService;

import java.io.File;
import java.util.List;

public class UserController {
    private final UserService service;

    public UserController(){
        this(new File("./data", "users.bin"));
    }

    public UserController(File file){
        service = new UserService(file);
    }

    public List<User> getAll(){
        return service.getAll();
    }

    public Passenger createUser(String login, String password, String name, String surname) {
        return service.createUser(login,password,name,surname);
    }

    public Passenger getPassengerData (String login, String password){
        return service.getPassengerData(login,password);
    }


    public boolean deleteUser(String login) {
        return service.deleteUser(login);
    }

    public boolean deleteUser(User user) {
        return service.deleteUser(user);
    }

    public void save() {
        service.save();
    }

//    public static void main(String[] args) {
//        System.out.println("=========CREATING THREE INITIAL USERS DEV PURPOSE ONLY============");
//        UserController userMgr = new UserController();
//        Passenger ps1 = userMgr.createUser("slv01", "Password", "Slava", "Stepanchuk");
//        Passenger ps2 = userMgr.createUser("nst01","Passsss","Nastia", "Stepanchuk");
//        Passenger ps3 = userMgr.createUser("olex", "psv","Oleksandr", "Ivanov");
//
//        Passenger ps4 = userMgr.getPassengerData("slv01","Password");
//        userMgr.save();
//        List<User> allUsers = userMgr.getAll();
//        allUsers.forEach(System.out::println);
//
//        System.out.println("Retrieved passenger");
//        System.out.println(ps4);
//    }
}

