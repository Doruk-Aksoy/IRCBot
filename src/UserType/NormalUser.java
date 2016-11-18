package UserType;

public class NormalUser extends GameUser {
    public NormalUser(UserCredentials cred, Integer i, Integer s) {
        this.id = i;
        this.score = s;
        this.type = User_Type.USER_NORMAL;
        this.uc = cred;
    }
}
