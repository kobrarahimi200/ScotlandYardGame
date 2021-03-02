package logic;

/**
 * this class is used for the loading the json file with the exactly the format
 * of that
 *
 * @author kobra
 */
public class Stations {

    private int identifier;
    private Position position;
    private int[] tube;//cab
    private int[] bus;
    private int[] cab;//train
    private int[] boat;

    /**
     * gets the id
     *
     * @return
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * gets the position
     *
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     * gets the cab
     *
     * @return
     */
    public int[] getCab() {
        return cab;
    }

    /**
     * gets the bus array
     *
     * @return
     */
    public int[] getBus() {
        return bus;
    }

    /**
     * sets the identifier
     *
     * @param identifier
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    /**
     * sets the given position
     *
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * sets the tube
     *
     * @param tube
     */
    public void setTube(int[] tube) {
        this.tube = tube;
    }

    /**
     * sets the bus array
     *
     * @param bus
     */
    public void setBus(int[] bus) {
        this.bus = bus;
    }

    /**
     * sets the cab array
     *
     * @param cab
     */
    public void setCab(int[] cab) {
        this.cab = cab;
    }

    /**
     * sets the boat array
     *
     * @param boat
     */
    public void setBoat(int[] boat) {
        this.boat = boat;
    }

    /**
     * gets the tube array
     *
     * @return
     */
    public int[] getTube() {
        return tube;
    }

    /**
     * gets the boat array
     *
     * @return
     */
    public int[] getBoat() {
        return boat;
    }

}
