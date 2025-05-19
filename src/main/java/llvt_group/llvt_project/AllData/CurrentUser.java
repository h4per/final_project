package llvt_group.llvt_project.AllData;

public class CurrentUser {
    private static UserData currentUser;

    public static void setCurrentUser(UserData user) {
        currentUser = user;
    }

    public static UserData getCurrentUser() {
        return currentUser;
    }
}
