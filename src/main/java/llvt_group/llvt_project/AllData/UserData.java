package llvt_group.llvt_project.AllData;

public class UserData {
    private static Integer id;
    private static String username;
    private final  String password;

    public UserData(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public static Integer getId() {
        return id;
    }
    public static String getUsername() {
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
