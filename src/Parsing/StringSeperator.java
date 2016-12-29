package Parsing;

public class StringSeperator implements Parser {
    public StringSeperator() { }
    
    @Override public String parseSingle(String s) {
        return null;
    }
    
    @Override public String[] parse(String s, String regex) {
        return s.split(regex);
    }
}
