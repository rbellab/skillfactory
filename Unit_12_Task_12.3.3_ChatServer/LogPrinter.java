import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @breif Вспомогательный класс */
public class LogPrinter {

    /**
     * @breif Метод для печати в консоль сервера
     * @param logMessage - Сообщение, которое должно быть напечатано */
    public static void printLog(String logMessage) {
        SimpleDateFormat date_format = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]: ");
        System.out.println(date_format.format(new Date(new Date().getTime())) + logMessage);
    }

    /**
     * @brief Метод для печати данных об ошибке
     * @param e - объект-дескриптор ошибки
     */
    public static void printError(Exception e) {
        System.err.println("\nОШИБКА: Что-то пошло не так! :(");
        e.printStackTrace(System.err);
    }

}
