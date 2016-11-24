package ircbot.Features;

import java.util.HashMap;

public class IRCBot_FeatureList {
    private HashMap<String, IRCBot_Feature> features;
    
    public IRCBot_FeatureList() {
        // delegate responsibility to builder
        IRCBot_FeatureList_Builder FB = new IRCBot_FeatureList_Builder();
        this.features = FB.build();
    }
    
    public IRCBot_Feature getFeature(String f) {
        return features.get(f);
    }
    
    public void addFeature(String f, IRCBot_Feature IF) {
        features.put(f, IF);
    }
}
