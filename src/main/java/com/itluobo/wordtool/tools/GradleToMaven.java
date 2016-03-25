package com.itluobo.wordtool.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kenvi on 16/2/16.
 */
public class GradleToMaven {
    private static final Pattern pattern = Pattern.compile("'([^:]+):([^:]+):([^']+)'");

    public static void convert(String[] args) throws Exception{
        String fileName = "/Users/kenvi/code/study/wechat/build.gradle";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

        String line;

        while( (line = bufferedReader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if(matcher.find()) {
                System.out.println("<dependency>\n" +
                        "            <groupId>" + matcher.group(1) + "</groupId>\n" +
                        "            <artifactId>" + matcher.group(2) + "</artifactId>\n" +
                        "            <version>" + matcher.group(3) + "</version>\n" +
                        "        </dependency>");
            }
        }
    }
}
