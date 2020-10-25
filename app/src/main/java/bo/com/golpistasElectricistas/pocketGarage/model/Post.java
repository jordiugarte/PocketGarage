package bo.com.golpistasElectricistas.pocketGarage.model;

public class Post {

    private int articleId;
    private String thumbnail;
    private String shortDescription;
    private String title;
    private double price;

    public Post(int userId, String thumbnail, String shortDescription, String title, double price) {
        this.articleId = userId;
        this.thumbnail = thumbnail;
        this.shortDescription = shortDescription;
        this.title = title;
        this.price = price;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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