package entities;

import java.io.Serializable;

public class User implements Serializable {
    private final Passenger passenger;
    private final String login;
    private final String password;

    public User(String login, String password, String name, String surname) {
        if (name != null & surname != null & login !=null & password !=null) {
            this.passenger = new Passenger(name, surname);
            this.login = login;
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid arguments: null is not accepted");
        }
    }

    public Passenger getPassengerData(){
        return this.passenger;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public String getLogin(){
        return this.login;
    }

    @Override
    public int hashCode() {
        return this.login.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        User user = (User) obj;
        if (!this.getPassengerData().equals(user.getPassengerData())) return false;
        return this.login.equals(user.getLogin());
    }

    @Override
    public String toString() {
        return String.format("{User: %s, %s}", login, this.getPassengerData());
    }
}
