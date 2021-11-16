public class Subject implements ISubject {
    protected String subjectType;
    protected String movingType;

    public Subject() {
        this("None", "None");
    }

    public Subject(String newSubjectType, String newMovingType) {
        this.subjectType = newSubjectType;
        this.movingType = newMovingType;
    }

    @Override
    public void moveIt() {
        System.out.println(this.subjectType + " is " + this.movingType + " right now!");
    }
}
