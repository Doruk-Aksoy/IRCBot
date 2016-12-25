package UserType;

public class NormalUser extends GameUser {
    public NormalUser() {
        score = 0;
        type = User_Type.USER_NORMAL;
        uc = null;
        ID = -1;
        game_id = 0;
        has_won = false;
    }
    
    public NormalUser(UserCredentials cred, Integer i, long s) {
        score = s;
        type = User_Type.USER_NORMAL;
        uc = cred;
        ID = i;
        game_id = 0;
        has_won = false;
    }
}
