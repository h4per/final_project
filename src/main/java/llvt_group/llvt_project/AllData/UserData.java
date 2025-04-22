package llvt_group.llvt_project.AllData;

public class UserData {
    private final Integer id;
    private final String username;
    private final String password;

    public UserData(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return username;
    }
}
