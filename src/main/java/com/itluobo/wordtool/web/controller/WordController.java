package com.itluobo.wordtool.web.controller;

import com.itluobo.wordtool.entity.Word;
import com.itluobo.wordtool.service.WordService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kenvi on 16/2/16.
 */
@RestController
@RequestMapping("/words")
public class WordController {

    @Resource
    private WordService wordService;

    @RequestMapping(value = "/getWordList",produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Word> getWordList(@RequestParam("book") String book,
                                  @RequestParam("begin")int begin,
                                  @RequestParam("end") int end) {
        return wordService.getWordList(book, begin, end);
    }

}
