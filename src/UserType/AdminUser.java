package UserType;

public class AdminUser extends GameUser {
    public AdminUser(UserCredentials cred, Integer i, Integer s) {
        this.id = i;
        this.score = s;
        this.type = User_Type.USER_ADMIN;
        this.uc = cred;
    }
}
