public class Loader {
    public static void main(String[] args) {
        moveMovable(new Aircraft());
        moveMovable(new Bicycle());
        moveMovable(new Box());
        moveMovable(new Ball());
    }

    public static void moveMovable(IMovable movable) {
        movable.moveIt();
    }
}
