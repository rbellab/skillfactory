public class Loader {
    public static void main(String[] args) {
        Ball ball = new Ball();
        Bicycle bicycle = new Bicycle();
        Box box = new Box();
        Aircraft aircraft = new Aircraft();

        ball.moveIt();
        bicycle.moveIt();
        box.moveIt();
        aircraft.moveIt();
    }
}
