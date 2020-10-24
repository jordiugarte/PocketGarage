package bo.com.golpistasElectricistas.pocketGarage.model;

import java.util.List;

public class Post {

    private int userId;
    private String thmbnail;
    private String shortDescription;
    private String title;
    private double price;

    public Post(int userId, String thmbnail, String shortDescription, String title, double price) {
        this.userId = userId;
        this.thmbnail = thmbnail;
        this.shortDescription = shortDescription;
        this.title = title;
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getThmbnail() {
        return thmbnail;
    }

    public void setThmbnail(String thmbnail) {
        this.thmbnail = thmbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}