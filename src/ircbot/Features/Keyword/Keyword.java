package ircbot.Features.Keyword;

import java.util.ArrayList;

public class Keyword {
    private String word;
    // contains list of ids of users that are using this keyword
    private ArrayList<Integer> id_list;
    
    public Keyword() {
        word = "";
        id_list = new ArrayList();
    }
    
    public Keyword(String w) {
        word = w;
        id_list = new ArrayList();
    }
    
    public Keyword(String w, int id) {
        word = w;
        id_list = new ArrayList();
        id_list.add(new Integer(id));
    }
    
    public String getWord() {
        return word;
    }
    
    public ArrayList<Integer> getIDList() {
        return id_list;
    }
    
    public void setWord(String w) {
        word = w;
    }
    
    public void addID(int id) {
        id_list.add(id);
    }
    
    public void deleteID(int id) {
        id_list.remove(new Integer(id));
    }
}
