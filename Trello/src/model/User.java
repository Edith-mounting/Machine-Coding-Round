package model;

public class User {
    private String userId;
    private String name;
    private String email;

    public User(final String userId, final String name, final String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public User(final String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "{" +
                "userId: '" + userId + '\'' +
                ", name: '" + name + '\'' +
                ", email: '" + email + '\'' +
                '}';
    }
}
