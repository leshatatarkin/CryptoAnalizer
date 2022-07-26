package ru.javarush.cryptoanalyzer.tatarkin.comands;

import ru.javarush.cryptoanalyzer.tatarkin.contatns.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Coder extends MainMethods {

    abstract int getAction();

    protected List<Character> coding(List<String> strings, int keyCoder) { // шифруем
        List<Character> chars = toCharList(strings); // список с символами исходного текста
        List<Character> newChars = new ArrayList<>(); // новый список
        Map<Character, Integer> map = Strings.arrayToMap(); // Мапа <символ - позиция>

        for (char symbol : chars) {
            if (map.containsKey(Character.toLowerCase(symbol))) {
                int pos = Strings.arrayToMap().get(Character.toLowerCase(symbol));
                int newPos = (pos + keyCoder * getAction()) % Strings.ALPHABET.length;
                if (newPos < 0) {
                    newPos += Strings.ALPHABET.length;
                }
                newChars.add(Strings.ALPHABET[newPos]);
            }
        }
        return newChars;
    }

}
