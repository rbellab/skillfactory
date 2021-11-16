public abstract class Transport implements ITransport {
    protected String transportType = "None";
    protected String movingType = "None";

    private Transport() {
        this("None", "None");
    }

    public Transport(String newTransportType, String newMovingType) {
        this.transportType = newTransportType;
        this.movingType = newMovingType;
    }

    @Override
    public void moveIt() {
        System.out.println(this.transportType + " is " + this.movingType + " right now!");
    }
}
