package bo.com.golpistasElectricistas.pocketGarage.model;

public class User {
    private int ci;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String photo;
    private String bornDate;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int ci, String email, String password, String name, String lastName, String bornDate) {
        this.ci = ci;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.bornDate = bornDate;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
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
}
