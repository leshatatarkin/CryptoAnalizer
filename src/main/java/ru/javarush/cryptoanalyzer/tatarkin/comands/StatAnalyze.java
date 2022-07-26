package ru.javarush.cryptoanalyzer.tatarkin.comands;

import ru.javarush.cryptoanalyzer.tatarkin.entity.Result;
import ru.javarush.cryptoanalyzer.tatarkin.entity.ResultCode;
import ru.javarush.cryptoanalyzer.tatarkin.exception.ApplicationException;
import ru.javarush.cryptoanalyzer.tatarkin.util.PathFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class StatAnalyze extends MainMethods implements Action {

    @Override
    public Result execute(String[] parameters) {
        String txtFileIn = parameters[0];
        String txtFileOut = parameters[1];
        String txtFileDict = parameters[2];//словарь

        Path path = Path.of(PathFinder.getRoot() + txtFileIn);
        Path pathResult = Path.of(PathFinder.getRoot() + txtFileOut);
        Path pathDict = Path.of(PathFinder.getRoot() + txtFileDict);
        try {
            List<String> stringsEnCoder = Files.readAllLines(path); // зашифрованный текст
            List<String> stringsDict = Files.readAllLines(pathDict); // текст автора для анализа
            List<Character> result = getResult(stringsEnCoder, stringsDict);
            writeToFile(pathResult, result); // записываем результат в файл
        } catch (IOException e) {
            throw new ApplicationException("Not find", e);
        }
        return new Result(ResultCode.OK, "read all bytes " + path);
    }


    @Override
    List<Character> coding(List<String> strings, int keyCoder) {
        return null;
    }

    private List<Character> getResult(List<String> stringsEnCoder, List<String> stringsDict) {
        //1. Получаем статистику по текстам и сортируем по значению
        List<Character> LetterOld = sortedMap(getRateLetter(stringsEnCoder)); // стата зашифрованного текста
        List<Character> LetterNew = sortedMap(getRateLetter(stringsDict)); // стата словаря

        //2. сопоставляем буквы по статистике (по популярности). На выходе Мапа "старый ключ - новый ключ"
        Map<Character, Character> compareMap = getCompareMap(LetterOld, LetterNew);

        //3. Заменяем буквы в зашифрованном тексте на новые по мапе "старый ключ - новый ключ"
        return encoding(toCharList(stringsEnCoder), compareMap);

    }

    private Map<Character, Integer> getRateLetter(List<String> strings) { // получение статистики по буквам
        Map<Character, Integer> rateLetter = new TreeMap<>();
        List<Character> charList = toCharList(strings);

        for (Character character : charList) {
            int valueRate = 0;
            if (rateLetter.containsKey(Character.toLowerCase(character))) {
                valueRate = rateLetter.get(Character.toLowerCase(character)) + 1;
            }
            rateLetter.put(Character.toLowerCase(character), valueRate);
        }
        return rateLetter;
    }

    private List<Character> sortedMap(Map<Character, Integer> map) { //сортировка мапы по значению
        List<Integer> sortedList = new ArrayList<>(map.values());
        List<Character> sortedListByLetter = new LinkedList<>();
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        for (Integer integer : sortedList) {
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (integer.equals(entry.getValue()) && !sortedListByLetter.contains(entry.getKey())) {
                    sortedListByLetter.add(entry.getKey());
                }
            }

        }
        return sortedListByLetter;
    }

    private Map<Character, Character> getCompareMap(List<Character> oldRateLetter, List<Character> newRateLetter) { // мапа "старый ключ (зашифр) - новый ключ(словарь)" по стат.анализу
        Map<Character, Character> compareMap = new HashMap<>();

        int length = oldRateLetter.size() < newRateLetter.size() ? oldRateLetter.size() : newRateLetter.size();
        for (int i = 0; i < length; i++) {
            compareMap.put(oldRateLetter.get(i), newRateLetter.get(i));
        }
        return compareMap;
    }

    private List<Character> encoding(List<Character> oldLetter, Map<Character, Character> compareMap) { // заменяем буквы в зашифрованном тексте по статистике
        List<Character> newList = new LinkedList<>();

        for (Character letter : oldLetter) {
            if (compareMap.containsKey(letter)) {
                newList.add(compareMap.get(letter));
            }
        }
        return newList;
    }
}
