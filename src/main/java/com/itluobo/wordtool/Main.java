package com.itluobo.wordtool;

import com.itluobo.wordtool.dao.WordDao;
import com.itluobo.wordtool.entity.Word;
import com.itluobo.wordtool.service.impl.WordBookServiceImpl;
import net.lingala.zip4j.core.ZipFile;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import org.htmlparser.parserapplications.StringExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kenvi on 16/2/15.
 */
public class Main {
     public static void main(String[] args) throws Exception{
//         WordBookServiceImpl wordService = new WordBookServiceImpl();
//         String destDir = "/tmp/epub/";
//         String bookName = "/Users/hannahzhang/Downloads/Superforecasting The Art and Science of Prediction by Philip E. Tetlock and Dan Gardner.epub";
//
//         ZipFile zipFile = new ZipFile(bookName);
//         zipFile.extractAll(destDir);
//         EpubReader epubReader = new EpubReader();
//         Book book = epubReader.readEpub(new FileInputStream(bookName));
//         List<String> titles = book.getMetadata().getTitles();
//         System.out.println(titles.get(0));
//         List<Resource> resourceList = book.getContents();
//         int chapter = 0;
//         WordDao wordDao = new WordDao();
//         for (Resource resource : resourceList) {
//             String path = destDir + "OEBPS/" + resource.getHref();
//             StringExtractor extractor = new StringExtractor(path);
//             String content = extractor.extractStrings(false);
//             String[] words = content.split(" ");
//             for (String w : words) {
//                 if(wordDao.countFamiliar(w) == 0) {
//                     wordService.addWord(w, chapter);
//                 }else {
//                     System.out.println("already seen:" + w);
//                 }
//             }
//             chapter++;
//         }
//
//         File file = new File(destDir);
//         if(file.exists()) {
//             file.delete();
//         }
//
//         List<Word> wordList = wordService.getWordList();
//
//         Set<Word> wordSet = new HashSet<Word>();
//
//         for(Word w : wordList) {
//             w.setBook(titles.get(0));
//             wordSet.add(w);
//         }
//
//         for(Word word : wordSet) {
//            wordDao.addWord(word);
//         }


     }
}
