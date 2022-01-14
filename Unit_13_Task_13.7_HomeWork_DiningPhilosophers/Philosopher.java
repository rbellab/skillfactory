///-----------------------------------------------------------------------///
/// @file Philosopher.java                                                ///
/// @brief Contains the implementation of Philosopher                     ///
///-----------------------------------------------------------------------///
/// @copyright (c) 2021 by Roman Berngardt. All rights are absolutely not ///
/// reserved.                                                             ///
///                                                                       ///
/// PLEASE FEEL FREE TO ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE    ///
/// HEADER AT ALL.                                                        ///
///                                                                       ///
/// This code is free software; you can redistribute it and/or modify it  ///
/// without any restrictions.                                             ///
///                                                                       ///
/// This code is distributed as a part of home work @SkillFactory in      ///
/// the hope that it will work correctly and will be useful, but WITHOUT  ///
/// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or ///
/// FITNESS FOR A PARTICULAR PURPOSE. JUST ENJOY IT! :)                   ///
///-----------------------------------------------------------------------///
/// File created on: 2022-01-14                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///

import java.util.Date;

/**
 * The {@code Philosopher} contains the implementation of the Philosopher
 *
 * @version 1.0
 */
public class Philosopher implements Runnable {
    // Возможные состояния "философа"
    private enum PhilosopherStates {THINKING, HUNGRY, EATING, STARVING}

    private final long MAX_HUNGRY_TIME = 5000L; // Максимальное время в ожидании пищи
    private PhilosopherStates currentState;     // Текущий статус "философа"
    private int philosopherId;                  // Личный номер "философа"
    private DiningTable diningTable = null;     // Ссылка на объект обеденного стола
    private Fork fork = null;                   // Ссылка на вилку
    private Knife knife = null;                 // Ссылка на нож
    private long hungryBeginTime = 0L;          // Время наступления "состояния голода"

    /**
     * @brief Конструктор класса
     * @param philosopherId - личный номер философа
     * @param diningTable - ссылка на объект обеденного стола
     */
    public Philosopher(int philosopherId, DiningTable diningTable) {
        this.philosopherId = philosopherId + 1;
        this.diningTable = diningTable;
        System.out.println("Философ №" + this.philosopherId + " сел за стол.");
    }

    /**
     * @brief Метод обработки конечного автомата состояний "философа"
     */
    private void processPhilosopherFsm() {
        switch (currentState) {
            // Если "философ" думает ...
            case THINKING: {
                try {
                    // Думаем некоторое время
                    Thread.sleep(Helper.getRandomNumber(0, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Философ №" + this.philosopherId + " голоден!");
                // Меняем состояние на "голод"
                currentState = PhilosopherStates.HUNGRY;
            }
            break;

            // Если "философ" проголодался ...
            case HUNGRY: {
                if (hungryBeginTime == 0L) {
                    // Засекаем время наступления состояния голода
                    hungryBeginTime = new Date().getTime();
                }

                // Проверяем, как долго философ уже голодает
                if (MAX_HUNGRY_TIME < (new Date().getTime() - hungryBeginTime)) {
                    System.out.println("Философ №" + this.philosopherId + " погибает от голода!");
                    // Увы, но "философ" погибает с голодухи ... :( [deadlock detected! ;)]
                    currentState = PhilosopherStates.STARVING;
                    break;
                }

                // Пытаемся взять вилку ...
                fork = diningTable.getFork();
                if (null != fork) {
                    // Получилось взять вилку -> пробуем взять нож
                    knife = diningTable.getKnife();
                    if (null != knife) {
                        // Получилось взять и нож тоже -> кушаем!
                        hungryBeginTime = 0L; // Больше не голодаем!
                        System.out.println("Философ №" + this.philosopherId + " кушает.");
                        currentState = PhilosopherStates.EATING;
                    } else {
                        // Не получилось взять нож -> кладём обратно вилку!
                        diningTable.putFork(fork);
                        // Ждём некоторое время пред тем, как попытаться снова
                        try {
                            Thread.sleep(Helper.getRandomNumber(0, 100));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            break;

            // Если философ кушает ...
            case EATING: {
                try {
                    // Кушаем некоторе время
                    Thread.sleep(Helper.getRandomNumber(0, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Поели -> возвращаем столовые приборы обратно ...
                diningTable.putFork(fork);
                diningTable.putKnife(knife);

                // ... и снова начинаем усиленно думать.
                System.out.println("Философ №" + this.philosopherId + " думает.");
                currentState = PhilosopherStates.THINKING;
            }
            break;

            case STARVING: {
                // Играет "похоронный марш" ...
            }
            break;

            default: {
                // Сюда мы попасть никак не должны!
            }
            break;
        }
    }

    @Override
    public void run() {
        System.out.println("Философ №" + this.philosopherId + " думает.");
        currentState = PhilosopherStates.THINKING;
        while (true) {
            // Пробегаемся по всем возможным состояниям "философа".
            processPhilosopherFsm();
        }
    }
}
