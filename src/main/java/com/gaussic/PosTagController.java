package com.gaussic;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.json.JSONObject;

@Controller
@RequestMapping("/postag")
public class PosTagController {

    private StanfordSegmenterUtil segmenter = new StanfordSegmenterUtil();

    // 页面
    @RequestMapping("")
    public String posTag() {
        return "index";
    }


    // 中文词性标注
    @RequestMapping(value = "/chinese", method = RequestMethod.POST)
    @ResponseBody
    public String chinesePosTagger(@ModelAttribute("sentence_chinese") String sentence) {
        JSONObject jo = new JSONObject();

        try {
            // 中文需要先进行分词
            String segmented = segmenter.getSegmentedString(sentence);
            String posTagged = StanfordPosTaggerUtil.getChineseTagger(segmented);

            jo.put("status", 0);
            jo.put("msg", "词性标注成功");
            jo.put("data", posTagged);

            System.out.println("词性标注结果: " + posTagged);
        } catch (Exception e) {
            jo.put("status", 1);
            jo.put("msg", "词性标注失败!!!");
            jo.put("data", "");
        }

        return jo.toString();
    }


    // 英文词性标注
    @RequestMapping(value = "/english", method = RequestMethod.POST)
    @ResponseBody
    public String englishPosTagger(@ModelAttribute("sentence_english") String sentence) {
        JSONObject jo = new JSONObject();

        try {
            String posTagged = StanfordPosTaggerUtil.getEnglishTagger(sentence);

            jo.put("status", 0);
            jo.put("msg", "Pos Tagging Succeed!!!");
            jo.put("data", posTagged);

            System.out.println("Pos Tagging Result：" + posTagged);
        } catch (Exception e) {
            jo.put("status", 1);
            jo.put("msg", "Pos Tagging Failed!!!");
            jo.put("data", "");
        }
        return jo.toString();
    }


}
