package ru.javarush.cryptoanalyzer.tatarkin.controller;

import ru.javarush.cryptoanalyzer.tatarkin.comands.Action;
import ru.javarush.cryptoanalyzer.tatarkin.entity.Result;

public class MainController {
    public Result execute(String command, String[] parameters) {
        Action action = Actions.find(command);
        Result result = action.execute(parameters);
        return result;
    }
}
