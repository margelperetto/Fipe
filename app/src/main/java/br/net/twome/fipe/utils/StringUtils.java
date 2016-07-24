package br.net.twome.fipe.utils;

import java.text.Normalizer;

public class StringUtils {

    public static String retirarAcentos(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        return str.replaceAll("[^\\p{ASCII}]", "");
    }
}
