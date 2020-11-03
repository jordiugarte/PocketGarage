package bo.com.golpistasElectricistas.pocketGarage.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "article_table")
public class Article {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "articleId")
    private int articleId;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "photos")
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

    @ColumnInfo(name = "state")
    private boolean newState;

    @ColumnInfo(name = "category")
    private int category;

    public Article(int articleId, int userId, List<String> photos, String shortDescription, String description, String title, long timestamp, double price, boolean newState, int category) {
        this.articleId = articleId;
        this.userId = userId;
        this.photos = photos;
        this.shortDescription = shortDescription;
        this.description = description;
        this.title = title;
        this.timestamp = timestamp;
        this.price = price;
        this.newState = newState;
        this.category = category;
    }

    public Article() {
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

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public boolean isNewState() {
        return newState;
    }

    public void setNewState(boolean newState) {
        this.newState = newState;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
