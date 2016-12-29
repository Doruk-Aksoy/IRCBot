package Parsing;

public interface Parser {
    public static final String spaces = "\\s+";
    public static final String spaces_andor_comma = "\\s*^\\d*[a-zA-Z][a-zA-Z0-9]*$(,|\\s)\\s*";
    
    public String[] parse(String s, String regex);
    public String parseSingle(String s);
}
