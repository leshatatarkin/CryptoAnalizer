package ru.javarush.cryptoanalyzer.tatarkin.comands;

import ru.javarush.cryptoanalyzer.tatarkin.entity.Result;

public interface Action {
    Result execute(String[] parameters);
}
