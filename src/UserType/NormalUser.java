package UserType;

import java.util.ArrayList;

public class NormalUser extends GameUser {
    public NormalUser(UserCredentials cred, Integer i, long s) {
        this.score = s;
        this.type = User_Type.USER_NORMAL;
        this.uc = cred;
        this.ID = i;
        this.game_id = 0;
    }
}
