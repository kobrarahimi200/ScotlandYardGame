package logic;

/**
 * creates an array of station
 *
 * @author Reyhan
 */
public class JsonStation {

    private Stations[] stations;

    /**
     * sets the given stations
     *
     * @param s
     */
    public void setS(Stations[] s) {
        this.stations = s;
    }

    /**
     * getst all stations
     *
     * @return
     */
    public Stations[] getStations() {
        return stations;
    }

}
