package Parsing;

public class StringSeperator implements Parser {
    public StringSeperator() { }
    
    public String[] parse(String s) {
        return s.split("\\s+");
    }
}
