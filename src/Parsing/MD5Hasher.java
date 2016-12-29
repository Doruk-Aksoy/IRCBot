package Parsing;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5Hasher implements Parser {
    public MD5Hasher() { }
    
    @Override public String[] parse(String s, String regex) {
        return null;
    }
    
    @Override public String parseSingle(String s) {
        String res;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = null;
            try {
                hash = md.digest(s.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(MD5Hasher.class.getName()).log(Level.SEVERE, null, ex);
                res = "";
            }
            BigInteger bi = new BigInteger(1, hash);
            res = String.format("%0" + (hash.length << 1) + "X", bi);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5Hasher.class.getName()).log(Level.SEVERE, null, ex);
            res = "";
        }
        return res;
    }
}
