package com.gaussic;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.StringUtils;

import java.util.List;
import java.util.Properties;

/**
 * Created by dzkan on 2016/8/21.
 * 斯坦福分词器，载入data较慢，但如果一次性处理大量数据的话，使用效果很好
 */
public class StanfordSegmenterUtil {

    private CRFClassifier<CoreLabel> segmenter;

    public void initSegmenter() {
        System.out.println("..........分词初始化......");
        Properties props = new Properties();
        props.setProperty("sighanCorporaDict", "edu/stanford/nlp/models/segmenter/chinese");
        props.setProperty("serDictionary", "edu/stanford/nlp/models/segmenter/chinese/dict-chris6.ser.gz");
        props.setProperty("inputEncoding", "UTF-8");
        props.setProperty("sighanPostProcessing", "true");
        segmenter = new CRFClassifier<>(props);
        segmenter.loadClassifierNoExceptions("edu/stanford/nlp/models/segmenter/chinese/ctb.gz", props);
        System.out.println("..........初始化完成..........");
    }

    public String getSegmentedString(String sentence) throws Exception {
        List<String> segmented = segmenter.segmentString(sentence);
        return StringUtils.join(segmented, " ");
    }

    public StanfordSegmenterUtil() {
        initSegmenter();
    }
}
