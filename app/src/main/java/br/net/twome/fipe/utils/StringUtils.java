package br.net.twome.fipe.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

    public static String retirarAcentos(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        return str.replaceAll("[^\\p{ASCII}]", "");
    }

    public static String quoteSpaces(String str){
        String[] strs = str.split("\\s");
        StringBuilder b = new StringBuilder();
        for(String s : strs){
            b.append(Pattern.quote(s)+"(.*)");
        }
        return "(.*)"+b.toString();
    }
}
