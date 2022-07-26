package ru.javarush.cryptoanalyzer.tatarkin.comands;

import ru.javarush.cryptoanalyzer.tatarkin.entity.Result;
import ru.javarush.cryptoanalyzer.tatarkin.entity.ResultCode;
import ru.javarush.cryptoanalyzer.tatarkin.exception.ApplicationException;
import ru.javarush.cryptoanalyzer.tatarkin.util.PathFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class MainMethods {
    abstract List<Character> coding(List<String> strings, int keyCoder);

    public Result execute(String[] parameters) {
        String txtFileIn = parameters[0];
        String txtFileOut = parameters[1];
        int keyCoder = Integer.parseInt(parameters[2]);

        Path path = Path.of(PathFinder.getRoot() + txtFileIn);
        Path pathResult = Path.of(PathFinder.getRoot() + txtFileOut);

        try {
            List<String> strings = Files.readAllLines(path);
            writeToFile(pathResult, coding(strings, keyCoder));
        } catch (IOException e) {
            throw new ApplicationException("Not find", e);
        }
        return new Result(ResultCode.OK, "read all bytes " + path);

    }

    protected void writeToFile(Path pathResult, List<Character> encoding) {
        String stringText = "";
        for (Character character : encoding) {
            stringText = stringText.concat(Character.toString(character));
            try {
                Files.writeString(pathResult, stringText);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected List<Character> toCharList(List<String> strings) { // разбиваем на символы
        List<Character> symbolList = new ArrayList<>();
        for (String string : strings) {
            char[] chars = string.toCharArray();
            for (char aChar : chars) {
                symbolList.add(aChar);
            }
        }
        return symbolList;
    }
}
