import java.util.ArrayList;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class ChatServer implements Runnable {

    private final int SERVER_PORT = 1234; // Номер порта чат-сервера
    private ServerSocket serverSocket = null; // Серверный сокет
    private ArrayList<ClientHandler> clientList = null; // Список подключенных клентов

    public static void main(String[] args) {
        try {
            new ChatServer().run(); // Создаём и запускаем сервер
        } catch (Exception e) {
            LogPrinter.printError(e);
        }
    }

    /**
     * @brief "Опасный" конструктор сервера */
    public ChatServer() throws IOException {
        this.clientList = new ArrayList<>(); // Создаём список клиентов
        this.serverSocket = new ServerSocket(SERVER_PORT); // Создаём серверный сокет
        InetAddress serverAddress = InetAddress.getLocalHost(); // Получаем адрес сервера
        LogPrinter.printLog("Сервер запущен на " + serverAddress.getHostAddress() + "@" + SERVER_PORT);
    }

    /**
     * @brief Метод посылает введённое одним из клиентов сообщение всем клиентам
     * @param chatMessage - сообщение, которое нужно всем клентам послать */
    public void sendToAll(String chatMessage) {
        // Пробегаемся по всем клиентам
        for (ClientHandler clentHandler : clientList) {
            // Пересылаем сообщение всем подключенным клиентам
            clentHandler.receiveMessage(chatMessage, true);
        }
    }

    /**
     * @brief Метод удалят отключившегося клиента из списка
     * @param clientHandler - объект клиент-хэндлера, ктороый должен быть удалён из списка */
    public void removeClientHandler(ClientHandler clientHandler) {
        clientList.remove(clientHandler); // Удаляем из списка клиентов
    }

    @Override
    public void run() {
        while (true) {
            try {
                LogPrinter.printLog("Ожидаем клиентов ...");
                // Слушаем сокет и в случае подключения нового клиента создаём
                // для него хэндлер и заносим его в список
                this.clientList.add(new ClientHandler(this.serverSocket.accept(), this));
                LogPrinter.printLog("Новый клиент подключен.");
            } catch (Exception e) {
                LogPrinter.printError(e);
            }
        }
    }

}
