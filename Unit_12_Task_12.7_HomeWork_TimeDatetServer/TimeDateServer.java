///-----------------------------------------------------------------------///
/// @file TimeDateServer.java                                             ///
/// @brief Contains the implementation of TimeDateServer class            ///
///-----------------------------------------------------------------------///
/// @copyright (c) 2022 by Roman Berngardt. All rights are absolutely not ///
/// reserved.                                                             ///
///                                                                       ///
/// PLEASE FEEL FREE TO ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE    ///
/// HEADER AT ALL.                                                        ///
///                                                                       ///
/// This code is free software; you can redistribute it and/or modify it  ///
/// without any restrictions.                                             ///
///                                                                       ///
/// This code is distributed as a part of home work @SkillFsctory in      ///
/// the hope that it will work correctly and will be useful, but WITHOUT  ///
/// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or ///
/// FITNESS FOR A PARTICULAR PURPOSE. JUST ENJOY IT! :)                   ///
///-----------------------------------------------------------------------///
/// File created on: 2022-01-11                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * {@code TimeDateServer} содержит реализацию простого сервера времени
 *
 * @version 1.0 */
public class TimeDateServer implements Runnable {

    private final int SERVER_PORT = 5555; // Порт, который "слушает" сервер
    private ServerSocket serverSocket = null; // Серверный сокет

    /**
     * {@code ClientHandler} содержит реализацию коммуникации с клиентом
     *
     * @version 1.0 */
    class ClientHandler implements Runnable {
        private Socket clientSocket = null; // Клиентский сокет
        private BufferedReader bufferedReader = null; // "Читатель" данных от клиента
        private PrintWriter printWriter = null; // "Писатель" данных, адресованных клиенту

        /**
         * @breif "Опасный" конструктор обработчика клиента
         * @param clientSocket - клиентский сокет */
        public ClientHandler(Socket clientSocket) throws IOException {
            this.clientSocket = clientSocket;
            // создаём объект "читатель"
            this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // создаём объект "писатель"
            this.printWriter = new PrintWriter(clientSocket.getOutputStream());
            // сообщаем, что клиент подключился
            System.out.println("Подключился клиент на " + clientSocket.getLocalSocketAddress());
        }

        /**
         * Собственно обработчик, который будет запущен в отдельном потоке */
        @Override
        public void run() {
            // Отсылаем приветсвие клиенту.
            sendMessage("Добро пожаловать на сервер даты и времени!");
            boolean isRunning = true; // Флаг, который показывает, что нужно продолжать обработку
            while(isRunning) {
                try {
                    // Читаем данные от клиента
                    String rcvData = receiveMessage();
                    // Если сокет закрыт со стороны клиента - завершаем работу.
                    if (null == rcvData) break;
                    // Если сокет не закрыт и данные "не пусты" - обрабатываем их
                    if (!rcvData.isEmpty()) {
                        switch (rcvData.toUpperCase()) {
                            case "GETTIME":
                            case "GET TIME": {
                                // Поступил запрос чтобы сообщить текущее время - сообщаем
                                SimpleDateFormat current_time = new SimpleDateFormat("HH:mm:ss");
                                sendMessage("Текущее время на сервере: " + current_time.format(new Date(new Date().getTime())));
                            }
                            break;

                            case "GETDATE":
                            case "GET DATE": {
                                // Поступил запрос чтобы сообщить текущую дату - сообщаем
                                SimpleDateFormat current_date = new SimpleDateFormat("yyyy-MM-dd");
                                sendMessage("Текущая дата на сервере: " + current_date.format(new Date(new Date().getTime())));
                            }
                            break;

                            case "DISCONNECT": {
                                // Поступил запрос на разъединение - разъединяем
                                sendMessage("До новых встреч!");
                                try {
                                    // Даём возможность клиенту получить прощальное сообщение и ...
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                // ... отключаемся.
                                isRunning = false;
                            }
                            break;

                            default: {
                                // Введена неподдерживаемая команда
                                sendMessage("Команда '" + rcvData + "' не поддерживается!");
                            }
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                clientSocket.close();
                System.out.println("Клиент завершил сеанс.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * @brief Посылает данные клиенту
         * @param message - Данные, которые нужно отослать клиенту */
        private void sendMessage(String message) {
            printWriter.println(message);
            printWriter.flush();
        }

        /**
         * @breief Читает данные от клиента
         * @return возвращает данные, полученные от клиента */
        private String receiveMessage() throws IOException {
            return bufferedReader.readLine();
        }
    }

    /**
     * @brief - точка входа в программу
     * @param args - не используется в данный моемент */
    public static void main(String[] args) {
        try {
            new TimeDateServer().run(); // Создаём и запускаем сервер
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief - "Опасный" конструктор класса TimeDateServer */
    public TimeDateServer() throws IOException {
        this.serverSocket = new ServerSocket(SERVER_PORT); // Создаём серверный сокет
        InetAddress serverAddress = InetAddress.getLocalHost(); // Получаем адрес сервера
        System.out.println("Сервер запущен на " + SERVER_PORT + "@" + serverAddress.getHostAddress());
    }

    /**
     * @breif Делает вс. полезную работу */
    @Override
    public void run() {
        while (true) {
            System.out.println("Ждём подключений ...");
            try {
                // Создаём новый поток и запускаем в нём обработчик клиента
                new Thread(new ClientHandler(serverSocket.accept())).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
