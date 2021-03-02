package logic;

/**
 * choose the best route according to startegy when the play is AI.
 *
 * @author kobra
 */
public class Choose {

    private Tickets t;
    private Station s;
    private double w;

    /**
     * default constructor
     */
    public Choose() {

    }

    /**
     * is used for the AI
     *
     * @param ticket
     * @param s
     */
    public Choose(Tickets ticket, Station s) {
        this.t = ticket;
        this.s = s;
    }

    /**
     * third constructor for selecting the best route with the weight
     *
     * @param ticket
     * @param s
     * @param weight
     */
    public Choose(Tickets ticket, Station s, double weight) {
        this.t = ticket;
        this.s = s;
        this.w = weight;
    }

    /**
     * gets the ticket
     *
     * @return
     */
    public Tickets getTicket() {
        return t;
    }

    /**
     * gets the station
     *
     * @return
     */
    public Station getStation() {
        return s;
    }

    /**
     * gets the weight
     *
     * @return
     */
    protected double getWeight() {
        return this.w;
    }

}
