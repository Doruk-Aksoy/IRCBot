package Parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringShuffle implements Parser {
    @Override public String[] parse(String s) {
        return null;
    }
    
    // Uses Fisher-Yates algorithm
    @Override public String parseSingle(String s) {
        List<Character> chars = new ArrayList<>();
        for(char c : s.toCharArray())
            chars.add(c);
        StringBuilder B = new StringBuilder(s.length());
        while(!chars.isEmpty()) {
            int index = ThreadLocalRandom.current().nextInt(0, chars.size());
            B.append(chars.remove(index));
        }
        return B.toString();
    }
}
