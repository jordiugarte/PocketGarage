package bo.com.golpistasElectricistas.pocketGarage.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.List;

@Entity(tableName = "article_table")
public class Article {

    @ColumnInfo(name = "userId")
    private int userId;

    @Ignore
    private List<String> photos;

    @ColumnInfo(name = "shortDescription")
    private String shortDescription;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @ColumnInfo(name = "price")
    private double price;

    public Article(int userId, String title, String shortDescription, String description, List<String> photos, long timestamp, double price) {
        this.userId = userId;
        this.photos = photos;
        this.shortDescription = shortDescription;
        this.description = description;
        this.title = title;
        this.timestamp = timestamp;
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
