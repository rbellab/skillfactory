/**
 * {@code Helper} класс-помощник
 *
 * @version 1.0
 */
public class Helper {
    /**
     * @brief Метод для генерации случайных чисел
     * @param min - минимальное число
     * @param max - максимальное число
     * @return Возвращает псевдо-случайное число в диапазоне [min..max]
     */
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private Helper() { }
}
