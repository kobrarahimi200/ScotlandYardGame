package logic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * each player has some attributes including id,is AI,weight for the choosed
 * station, type, number of taxi tickets, number of bus tickets, number of boat
 * tickets and number of train ticket.
 *
 * @author Reyhan
 */
public class Player {

    private int id;
    private final playerType type;
    private boolean isAI;
    private int boats;
    private int taxis;
    private int buses;
    private int trains;
    private int station;
    private int prevStation;
    private int aiID;
    private double weight;
    
    HashMap<Tickets, Integer> tickets = new HashMap<>();//stores all tickets

    /**
     * main constructor
     *
     * @param id
     * @param type
     * @param isAI
     * @param numOfTrains
     * @param numOfBuses
     * @param numOfTaxis
     * @param numOfBoats
     */
    public Player(int id, playerType type, boolean isAI, int numOfTrains, int numOfBuses, int numOfTaxis,
            int numOfBoats) {
        this.id = id;
        this.type = type;
        this.isAI = isAI;
        this.weight = 0.0;
        tickets.put(Tickets.TRAIN, numOfTrains);
        tickets.put(Tickets.BUS, numOfBuses);
        tickets.put(Tickets.TAXI, numOfTaxis);
        tickets.put(Tickets.BOAT, numOfBoats);
        this.station = 0;
        this.prevStation = 0;
        if (isAI) {
            aiID = 1;
        } else {
            aiID = 0;
        }
    }

    /**
     * sets the weight for the selected station
     *
     * @param weight
     */
    void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * if the player is the AI then return the weight of the station otherwise
     * return o.
     *
     * @return
     */
    protected double getWeight() {
        return this.weight;
    }

    /**
     * return the player type
     *
     * @return
     */
    playerType getPlayerType() {
        return this.type;
    }

    /**
     * increment the number of given ticket type
     *
     * @param ticketTytpe
     */
    void incTicket(Tickets ticketTytpe) {
        if (null != ticketTytpe) {
            tickets.put(ticketTytpe, tickets.get(ticketTytpe) + 1);
        }
    }

    /**
     * decrease the number of given ticket type
     *
     * @param ticketTytpe
     */
    void decTicket(Tickets ticketTytpe) {
        if (null != ticketTytpe) {
            tickets.put(ticketTytpe, tickets.get(ticketTytpe) - 1);
        }
    }

    /**
     * used for logging
     *
     * @return
     */
    protected int getaiID() {
        return this.aiID;
    }

    /**
     * used for logging one is true AI zero is human
     *
     * @param aiID
     */
    protected void setAiID(int aiID) {
        this.aiID = aiID;
    }

    /**
     * gets the id of the given ticket
     *
     * @param ticket
     * @return
     */
    protected int getTicketType(Tickets ticket) {
        return tickets.get(ticket);
    }

    /**
     * return true if the current player is AI
     *
     * @return
     */
    protected boolean isAI() {
        return isAI;
    }

    /**
     * gets the number of taxis
     *
     * @return
     */
    protected int getNumOfTaxis() {
        return tickets.get(Tickets.TAXI);
    }

    /**
     * gets the number of buses
     *
     * @return
     */
    protected int getNumOfBuses() {
        return tickets.get(Tickets.BUS);
    }

    /**
     * gets the number of trains
     *
     * @return
     */
    protected int getNumOfTrains() {
        return tickets.get(Tickets.TRAIN);
    }

    /**
     * gets the number of boats
     *
     * @return
     */
    protected int getNumOfBoats() {
        return tickets.get(Tickets.BOAT);
    }

    /**
     * gets the current station
     *
     * @return
     */
    protected int getCurrStation() {
        return this.station;
    }

    /**
     * gets the previous station
     *
     * @return
     */
    protected int getPrevStation() {
        return this.prevStation;
    }

    /**
     * sets the given station
     *
     * @param station
     */
    protected void setCurrStation(int station) {
        this.station = station;
    }

    /**
     * sets the prevoius station
     *
     * @param station
     */
    protected void setprevStation(int station) {
        this.prevStation = station;
    }

    /**
     * gets all tickets
     *
     * @return
     */
    int[] getAllTickets() {
        return new int[]{getNumOfTrains(), getNumOfBuses(), getNumOfTaxis(), getNumOfBoats()};
    }

    /**
     * gets all expected stations of mister x
     *
     * @param expectedStations
     * @param ticket
     * @return
     */
    protected List<Station> expectedMrXStations(List<Station> expectedStations, Tickets ticket) {
        return null;
    }

    /**
     * increse the number of turns
     */
    protected void incNumOfTurns() {
    }

    /**
     * gets the number of turrns
     *
     * @return
     */
    protected int getnumOfTurns() {
        return 0;
    }

    /**
     * return the last position which mister x is shown
     *
     * @return
     */
    protected int lastShownPos() {
        return 0;
    }

    /**
     * add the given ticket to the journey list
     *
     * @param ticket
     */
    protected void addToJourneyList(Tickets ticket) {
    }

    /**
     * gets the journey list
     *
     * @return
     */
    protected List<Integer> getJournyList() {
        return new LinkedList<>();
    }

    /**
     * used for AI part of mister x
     *
     * @param board
     * @param p
     * @return
     */
    List<Station> getAllFreeNeighbors(Board b, Player[] p) {
        return null;
    }

    /**
     * is responsible when a player is ai and calculate the strategy and finally
     * choose a best route for moving.
     *
     * @param board
     * @param p
     * @param targetPositions
     */
    void AI(Board board, Player[] p, List<Station> targetPositions) {

    }

    /**
     * returns a choosed path
     *
     * @return
     */
    protected Choose getChoosedAI() {
        return new Choose();
    }

    /**
     * count the possible free stations
     *
     * @param board
     * @param p
     * @param s
     * @return
     */
    int countPossibleFreeStations(Player[] p, Station s) {
        return 0;
    }

    /**
     * this method is returning the smallest amount of ticket
     *
     * @return int the smallest amount
     */
    protected int getMinTicket() {
        int minValue = getAllTickets()[0];
        int len = 0;
        if (this.getPlayerType() == playerType.MISTERX) {
            len = Tickets.values().length;
        } else {
            len = Tickets.values().length - 1;
        }
        for (int i = 0; i < len; i++) {
            if (getAllTickets()[i] < minValue) {
                minValue = getAllTickets()[i];
            }
        }
        return minValue;
    }

    @Override
    public String toString() {
        return "player" + type + " " + id + "with tickets : " + trains + " " + buses + " " + taxis + " " + boats + '}';
    }

}
