package com.gaussic;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * Created by dzkan on 2016/8/21.
 * stanford pos tagger for chinese and english
 */
public class StanfordPosTaggerUtil {

    public static String getChineseTagger(String sentence) throws Exception {
        MaxentTagger tagger = new MaxentTagger("edu/stanford/nlp/models/pos-tagger/chinese-distsim/chinese-distsim.tagger");
        return tagger.tagString(sentence);
    }

    public static String getEnglishTagger(String sentence) throws Exception {
        MaxentTagger tagger = new MaxentTagger("edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger");
        return tagger.tagString(sentence);
    }

}
