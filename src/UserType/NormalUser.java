package UserType;

import java.util.ArrayList;

public class NormalUser extends GameUser {
    public NormalUser(UserCredentials cred, Integer i, Integer s) {
        this.score = s;
        this.type = User_Type.USER_NORMAL;
        this.uc = cred;
        this.keywords = new ArrayList<String>();
    }
}
