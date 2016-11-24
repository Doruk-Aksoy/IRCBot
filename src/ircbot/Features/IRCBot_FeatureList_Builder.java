package ircbot.Features;

import ConstantData.Feature_CD;
import java.util.HashMap;

public class IRCBot_FeatureList_Builder {
    public HashMap<String, IRCBot_Feature> build() {
        HashMap<String, IRCBot_Feature> fl = new HashMap<>();
        fl.put(Feature_CD.stat_evaluation, new IRCBot_StatEvaluator());
        fl.put(Feature_CD.notifier, new IRCBot_Notifier());
        return fl;
    }
}
