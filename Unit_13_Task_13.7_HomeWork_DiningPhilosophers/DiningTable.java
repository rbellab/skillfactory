///-----------------------------------------------------------------------///
/// @file DiningTable.java                                                ///
/// @brief Contains the implementation of DiningTable                     ///
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

import java.util.ArrayList;

/**
 * The {@code DiningTable} contains the implementation of the DiningTable
 *
 * @version 1.0
 */
public class DiningTable implements Runnable {

    private static int PHILOSOPHER_COUNT = 6; // Количество ужинающих "философов"

    private ArrayList<Fork> forkList = null; // "Столовый набор" вилок
    private ArrayList<Knife> knifeList = null; // "Столовый набор" ножей

    /**
     * @brife Конструктор класса
     */
    public DiningTable() {
        createForks(); // Раскладываем вилки
        createKnives(); // Раскладываем ножи
        createPhilosophers(); // "Приглашаем" философов за стол
    }

    /**
     * @brief Метод для создания вилок
     */
    private void createForks() {
        // Создаём новый "столовый набор" вилок и ...
        forkList = new ArrayList<>();
        for (int i = 0; i < (PHILOSOPHER_COUNT / 2); ++i) {
            // ... кладём туда новые вилки
            forkList.add(new Fork());
        }
    }

    /**
     * @brief Метод для создания ножей
     */
    private void createKnives() {
        // Создаём новый "столовый набор" ножей и ...
        knifeList = new ArrayList<>();
        for (int i = 0; i < (PHILOSOPHER_COUNT / 2); ++i) {
            // ... кладём туда новые ножи
            knifeList.add(new Knife());
        }
    }

    /**
     * @brief Метод для создания "философов"
     */
    private void createPhilosophers() {
        for (int i = 0; i < PHILOSOPHER_COUNT; ++i) {
            // Создаём новый поток и запускаем в нём "философа"
            new Thread(new Philosopher(i, this)).start();
        }
    }

    /**
     * @brief Потопобезопасный метод для взятия ножа из набора
     * @return возвращает взятый из набора нож или null если больше не осталось ножей
     */
    public synchronized Knife getKnife() {
        Knife knife = null;
        // Если в "наборе" ещё есть ножи, то ...
        if (!knifeList.isEmpty()) {
            // ... берём нож из набора
            knife = knifeList.get(knifeList.size() - 1);
            knifeList.remove(knife);
        }
        // Возвращаем "взятый" нож или null, если ножей больше не осталось
        return knife;
    }

    /**
     * @brief Потокобезопасный метод для уклдаки ножей в набор.
     * @param knife - нож, который нужно положить (вернуть) в столовый набор
     */
    public synchronized void putKnife(Knife knife) {
        // Кладём нож обратно в "набор"
        knifeList.add(knife);
    }

    /**
     * @brief Потопобезопасный метод для взятия вилки из набора
     * @return возвращает взятую из набора вилку или null если больше не осталось вилок
     */
    public synchronized Fork getFork() {
        Fork fork = null;
        // Если в "наборе" ещё есть вилки, то ...
        if (!forkList.isEmpty()) {
            // ... берём вилку из набора
            fork = forkList.get(forkList.size() - 1);
            forkList.remove(fork);
        }
        // Возвращаем "взятую" вилку или null, если вилок больше не осталось
        return fork;
    }

    /**
     * @brief Потокобезопасный метод для уклдаки вилок в набор.
     * @param fork - вилка, которую нужно положить (вернуть) в столовый набор
     */
    public synchronized void putFork(Fork fork) {
        // Кладём вилку обратно в "набор"
        forkList.add(fork);
    }

    @Override
    public void run() {
        while (true) {
            // Ждём пока "философы" покушают...
            try {
                Thread.sleep(1000); // Погружаем текущий поток в сон, чтобы не тратить ресурсы CPU
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @brief Точка входа в программу
     * @param args - в настоящее время не используется */
    public static void main(String[] args) {
        // Открываем новый "обеденный стол" для "философов".
        new DiningTable().run();
    }
}
