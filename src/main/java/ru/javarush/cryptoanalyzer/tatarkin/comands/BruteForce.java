package ru.javarush.cryptoanalyzer.tatarkin.comands;

import ru.javarush.cryptoanalyzer.tatarkin.contatns.Strings;

import java.util.*;

public class BruteForce extends Decoder {
    @Override
    protected List<Character> coding(List<String> strings, int keyCoder) {
        List<Character> chars = toCharList(strings); // список с символами исходного текста
        Map<Character, Integer> map = Strings.arrayToMap(); // Мапа <символ - позиция>
        Map<Integer, List<Character>> variantsFromKey = new HashMap<>();
        for (int i = 0; i < Strings.ALPHABET.length; i++) {
            List<Character> newChars = new ArrayList<>(); // новый список
            for (char symbol : chars) {
                if (map.containsKey(Character.toLowerCase(symbol))) {
                    int pos = Strings.arrayToMap().get(Character.toLowerCase(symbol));
                    int newPos = (pos + i * getAction()) % Strings.ALPHABET.length;
                    if (newPos < 0) {
                        newPos += Strings.ALPHABET.length;
                    }
                    newChars.add(Strings.ALPHABET[newPos]);
                }
            }
            variantsFromKey.put(i, newChars);// i - ключ . newChars- дешифрованная строка
        }
        return getBestResult(variantsFromKey);
    }

    private List<Character> getBestResult(Map<Integer, List<Character>> variantsFromKey) {
        Map<Integer, List<Character>> allVariants = new TreeMap<>();

        for (List<Character> value : variantsFromKey.values()) {
            int count = 0;
            for (int i = 0; i < value.size() - 1; i++) {
                if (value.get(i).equals(' ')) {
                    if (i < value.size() - 2) {
                        if (value.get(i + 2).equals(' ')) {
                            count++;
                        }
                    }
                    if (i < value.size() - 3) {
                        if (value.get(i + 3).equals(' ')) {
                            count++;
                        }
                    }
                }
            }
            allVariants.put(count, value);
        }
        List<List<Character>> bestResult = new ArrayList<List<Character>>(allVariants.values());
        return bestResult.get(bestResult.size() - 1);

    }
}
