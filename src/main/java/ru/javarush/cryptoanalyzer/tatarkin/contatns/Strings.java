package ru.javarush.cryptoanalyzer.tatarkin.contatns;

import java.util.HashMap;
import java.util.Map;

public class Strings {
    public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static Map<Character, Integer> arrayToMap() {
        Map<Character, Integer> alphabetMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length; i++) {
            alphabetMap.put(ALPHABET[i], i);
        }
        return alphabetMap;
    }

}
