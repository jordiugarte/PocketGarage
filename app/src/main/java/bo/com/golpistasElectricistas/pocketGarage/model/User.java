package bo.com.golpistasElectricistas.pocketGarage.model;

import androidx.room.Entity;

public class User {
    private String ci;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String photo;
    private String bornDate;
    private int phone;
    private double lastLogin;

    public User(String ci, String email, String password, String name, String lastName, String bornDate, int phone) {
        this.ci = ci;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.phone = phone;
    }

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public double getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(double lastLogin) {
        this.lastLogin = lastLogin;
    }
}
