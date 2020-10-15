package bo.com.golpistasElectricistas.pocketGarage.model;

import java.util.List;

public class Article {
    private int articleId;
    private List<String> photos;
    private String shortDescription;
    private String description;
    private String title;
    private long timestamp;
    private double price;

    public Article(int articleId, String title, String shortDescription, String description, List<String> photos, long timestamp, double price) {
        this.articleId = articleId;
        this.photos = photos;
        this.shortDescription = shortDescription;
        this.description = description;
        this.title = title;
        this.timestamp = timestamp;
        this.price = price;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
