package ru.javarush.cryptoanalyzer.tatarkin.console;

import ru.javarush.cryptoanalyzer.tatarkin.toplevel.Application;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private Application application;
    private boolean infinite;
    private final String nameFileIn = "text.txt";
    private final String nameFileOut = "result.txt";
    private final String nameFileDict = "dict.txt";

    private String[] args;
    private final String encode = "ENCODE";
    private final String decode = "DECODE";
    private final String bruteForce = "BRUTEFORCE";
    private final String statAnalyze = "STAT";


    public Menu() {
        this.scanner = new Scanner(System.in);
        this.infinite = true;
        this.application = new Application();
    }

    public void printMainMenu() { // вывод меню
        while (infinite) {
            System.out.println("Выберите пункт меню:\n" +
                    "1. Зашифровать \n" +
                    "2. Дешифровать\n" +
                    "3. Брутфорс\n" +
                    "4. Стат. анализ\n" +
                    "5. Выйти");
            processingUserCommand(getUserCommand());
        }
    }

    public int getCoderKey() { // вывод меню
        System.out.println("Введите значение ключа:");
        return getUserCommand();
    }

    public int getUserCommand() { // получение команды от пользователя
        return scanner.nextInt();
    }

    private void processingUserCommand(int userVoid) { // обратботка команды пользователя
        switch (userVoid) {
            case 1:
                args = new String[]{encode, nameFileIn, nameFileOut, Integer.toString(getCoderKey())};
                System.out.println(application.run(args));
                break;
            case 2:
                args = new String[]{decode, nameFileIn, nameFileOut, Integer.toString(getCoderKey())};
                System.out.println(application.run(args));

                break;
            case 3:
                args = new String[]{bruteForce, nameFileIn, nameFileOut, "0"};
                System.out.println(application.run(args));
                break;
            case 4:
                args = new String[]{statAnalyze, nameFileIn, nameFileOut, nameFileDict};
                System.out.println(application.run(args));
                break;
            case 5:
                System.out.println("Выход");
                infinite = false;
                break;
            default:
                System.out.println("Такой команды нет!");
                break;
        }
    }


}
