package ru.javarush.cryptoanalyzer.tatarkin.toplevel;

import ru.javarush.cryptoanalyzer.tatarkin.controller.MainController;
import ru.javarush.cryptoanalyzer.tatarkin.entity.Result;

import java.util.Arrays;

public class Application {
    private final MainController mainController;

    public Application() {
        this.mainController = new MainController();
    }

    public Result run(String[] args) {
        String command = args[0];
        String[] parameters = Arrays.copyOfRange(args, 1, args.length);
        return mainController.execute(command, parameters);
    }

}

