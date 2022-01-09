import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler implements Runnable {
    private ChatServer chatServer = null;
    private BufferedReader userInput = null;
    private PrintWriter dataWriter = null;
    private Socket clentSocket = null;
    private String userName = null;

    /**
     * @brief Конструктор клиент-хэндлера
     * @param clientSocket - клиентский сокет
     * @param chatServer - ссылка на объект чат-сервера */
    public ClientHandler(Socket clientSocket, ChatServer chatServer) {
        this.clentSocket = clientSocket;
        this.chatServer = chatServer;
        try {
            // создаём объект для чтения данных, которые вводит клиент
            this.userInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // создаём объект для вывода данных, предназначенных для клиента
            this.dataWriter = new PrintWriter(clientSocket.getOutputStream());
            // создаём новый поток и запускаем в нём клиент-хэндлер
            new Thread(this).start();
        } catch (Exception e) {
            LogPrinter.printError(e);
        }
    }

    /**
     * @brief Метод для получения данных клиентом
     * @param chatMessage - данные для клиента
     * @param carrigeReturn - нужно ли перводить строку */
    public void receiveMessage(String chatMessage, boolean carrigeReturn) {
        if (true == carrigeReturn) {
            this.dataWriter.println(chatMessage);
            this.dataWriter.print("=> ");
        } else {
            this.dataWriter.print(chatMessage);
        }
        this.dataWriter.flush();
    }

    /**
     * @brief Метод спрашивает вновь подключившегося клиента о том, как его называть */
    private void askForName() throws IOException {
        this.receiveMessage("Как Вас называть?\r\n=> ", false);
        this.userName = userInput.readLine();
        this.receiveMessage("Добро пожаловать в чат, " + this.userName + "!", true);
    }

    @Override
    public void run() {
        try {
            askForName(); // Спрашиваем, как называть клиента
            performChatting(); // Чаттимся пока пользоваетль не введёт "bye" или не закроект клиента
        } catch (Exception e) {
            LogPrinter.printError(e);
        } finally {
            try {
                this.clentSocket.close(); // Закрываем сокет
                this.clentSocket = null;
                this.chatServer.removeClientHandler(this); // Удаляем отключившегося клиента из списка
                LogPrinter.printLog("Клиент '" + this.userName + "' завершил работу.");
            } catch (IOException e) {
                LogPrinter.printError(e);
            }
        }
    }

    /**
     * @breif Метод для обработки клиентсокго ввода-вывода */
    private void performChatting() {
        while (true) {
            try {
                String userInputData = userInput.readLine();
                if (null == userInputData) {
                    // Клиент закрыл свой сокет -> отключаемся
                    break;
                }
                if (!userInputData.isEmpty()) {
                    // Введена непустая строка
                    if (userInputData.equalsIgnoreCase("BYE")) {
                        // Клиент ввёл команду "bye" -> отключаемся
                        break;
                    } else {
                        // Получаем и форматируем текущую дату
                        SimpleDateFormat date_format = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]: ");
                        userInputData = date_format.format(new Date(new Date().getTime())) + userInputData;
                        userInputData = userInputData.replace("]", " " + this.userName + "]");
                        // Послыаем данные всем подключенным клиентам
                        this.chatServer.sendToAll(userInputData);
                    }
                }
            } catch (Exception e) {
                LogPrinter.printError(e);
            }
        }
    }

}
