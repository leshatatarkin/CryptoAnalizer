package ru.javarush.cryptoanalyzer.tatarkin.controller;

import ru.javarush.cryptoanalyzer.tatarkin.comands.*;

public enum Actions {
    ENCODE(new Encoder()), DECODE(new Decoder()), BRUTEFORCE(new BruteForce()), STAT(new StatAnalyze());

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String command) {
        Action action = Actions.valueOf(command.toUpperCase()).action;
        return action;
    }
}
