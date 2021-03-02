package logic;

import java.util.LinkedList;
import java.util.List;

/**
 * create a new player with type of mister x, if the player is AI he should
 * choose the best next station for moving
 *
 * @author kobra
 */
public class MisterX extends Player {

    int numOfTurns;
    private int lastShowPos;
    private List<Integer> journeyBoard; // store all used ticket
    List<Station> neighborsMisterX;
    private Choose choose;
    private Board board; //board of the mister x

    public MisterX(int id, playerType type, boolean isAI, int numOfTrains, int numOfBuses,
            int numOfTaxis, int numOfBoats) {
        super(id, type, isAI, numOfTrains, numOfBuses, numOfTaxis, numOfBoats);
        this.numOfTurns = 0;
        lastShowPos = 0;
        journeyBoard = new LinkedList<>();
        choose = null;
    }

    @Override
    protected List<Integer> getJournyList() {
        return journeyBoard;
    }

    @Override
    protected int getnumOfTurns() {
        return numOfTurns;
    }

    @Override
    protected void incNumOfTurns() {
        numOfTurns++;
    }

    @Override
    protected int lastShownPos() {
        return lastShowPos;
    }

    @Override
    protected void addToJourneyList(Tickets ticket) {
        journeyBoard.add(ticket.ordinal());
    }

    @Override
    protected Choose getChoosedAI() {
        return this.choose;
    }

    /**
     * calculates the first condition (numOfDetectives - detectiviesAccess)*10
     * second condition :( numOfFreeNeighbors of neighbors / 13)*4
     *
     * @param board
     * @param p
     */
    @Override
    void AI(Board board, Player[] p, List<Station> targetPos) {
        this.board = board;
        List<Station> neighbors = getAllFreeNeighbors(board,p);
        boolean[] routes;
        if (!neighbors.isEmpty()) {
            double totalValue = 0.0;
            for (int i = 0; i < neighbors.size(); i++) {
                if (canPlayMisterX(this, this.getCurrStation(), neighbors.get(i))) {
                    routes = hasEnoughTicket(neighbors.get(i).getIdentifier());
                    for (int j = 0; j < routes.length; j++) {
                        if (routes[j] && this.getTicketType(Tickets.values()[j]) > 0) {
                            Tickets ticket = Tickets.values()[j];
                            Choose ch = chooseNeighbors(neighbors.get(i), totalValue, p, ticket, false);
                            if (ch != null && ch.getWeight() > totalValue) {
                                choose = ch;
                                totalValue = ch.getWeight();
                            }
                            if (this.getNumOfBoats() > 0) {//num of boats
                                ch = chooseNeighbors(neighbors.get(i), totalValue, p, ticket, true);
                                if (ch != null && ch.getWeight() > totalValue) {
                                    choose = ch;
                                    totalValue = ch.getWeight();
                                }
                            }
                        }
                    }
                }
            }
        } else {
            choose = null;
        }
    }

    /**
     * calculate the first and second conditions for weighting the nodes and
     * return the best choise. First calcuation :How many possible target
     * positions can be reached by all detectives in the next turn divided by
     * the total number of all possible target positions (for the detectives not
     * yet drawn, their old stations should still be taken into account)
     * Weighting: Number of achievable target positions by total number of
     * target positions * 10 second : ow far away is the " average possible
     * target position "
     *
     * @param station
     * @param total
     * @param p
     * @param board
     * @param ticket
     * @param useBlackTicket
     * @return
     */
    private Choose chooseNeighbors(Station station, double total, Player[] p,
            Tickets ticket, boolean useBlackTicket) {

        double ticketCount = 0.0;
        Choose choosed = null;
        double firstWeight;
        double secondWeight;
        if (!useBlackTicket) {
            this.decTicket(ticket);
        }
        firstWeight = ((p.length - 1) - numberOfDetectiveAccess(p, station)) * 10;
        secondWeight = countPossibleFreeStations(p, station) / 13.0 * 4.0;

        if (this.getMinTicket() > 2) {
            ticketCount = 3;
        } else {
            this.getMinTicket();
        }
        if ((firstWeight + secondWeight + ticketCount) > total) {
            total = (firstWeight + secondWeight + ticketCount);
            if (!useBlackTicket) {
                choosed = new Choose(ticket, station, total);
                this.setWeight(total);
            } else {
                choosed = new Choose(Tickets.BOAT, station, total);
                this.setWeight(total);
            }
        }
        if (!useBlackTicket) {
            this.incTicket(ticket);
        }
        return choosed;
    }

    /**
     * store all neighbors of neighbors of the current station of mister x that
     * they do not contain the detectives
     *
     * @param board
     * @param p
     * @param s is the all neighbors of mister x(neighborsMisterX list)
     * @return
     */
    @Override
    int countPossibleFreeStations(Player[] p, Station s) {

        boolean added;
        List<Station> st = new LinkedList<>();
        for (int i = 0; i < board.getAllStations().size(); i++) {
            added = false;
            if (s.isNeighbor(i + 1) && !st.contains(board.getAllStations().get(i))) {
                st.add(board.getAllStations().get(i));
                added = true;
            }
            for (int j = 1; j < p.length && added; j++) {
                if (!s.canPlay(i + 1, this)
                        || p[j].getCurrStation() == board.getAllStations().get(i).getIdentifier()) {

                    st.remove(board.getAllStations().get(i));
                }
            }
        }

        return st.size();
    }

    /**
     * gets all neighbors of mister x and store into a list and remove all nodes
     * contains detectives
     *
     * @param board
     * @param p
     * @return
     */
    @Override
    List<Station> getAllFreeNeighbors(Board board,Player[] p) {
        neighborsMisterX = new LinkedList<>();
        for (int i = 0; i < board.getAllStations().size(); i++) {
            if (board.getStation(this.getCurrStation()).isNeighbor(i + 1)) {
                neighborsMisterX.add(board.getAllStations().get(i));
            }
        }
        neighborsMisterX = removeDetectiveFrom(p);
        return neighborsMisterX;
    }

    /**
     * remove stations from neighborsMisterX which contains detectives
     *
     * @param p
     * @return
     */
    private List<Station> removeDetectiveFrom(Player[] p) {
        boolean deleted;
        for (int j = 1; j < p.length; j++) {
            deleted = false;
            for (int i = 0; i < neighborsMisterX.size() && !deleted; i++) {
                if (p[j].getCurrStation() == neighborsMisterX.get(i).getIdentifier()) {
                    neighborsMisterX.remove(i);
                    i--;
                    deleted = true;
                }
            }
        }
        return neighborsMisterX;
    }

    /**
     * counts the number of neighbor station of mister x which detective can
     * reach them
     *
     * @param board
     * @param p
     * @param s
     * @return
     */
    private int numberOfDetectiveAccess(Player[] p, Station s) {
        int x = 0;
        for (int i = 1; i < p.length; i++) {
            if (board.isNeighbor(p[i].getCurrStation(), s.getIdentifier())
                    && board.canPlay(s.getIdentifier(), p[i])) {
                x++;
            }
        }
        return x;
    }

    /**
     * checks if the current player has enough ticket to the current station
     *
     * @param board
     * @param p
     * @param current
     * @param s
     * @return
     */
    private boolean canPlayMisterX(Player p, int current, Station s) {

        boolean allowedToPlay = false;
        allowedToPlay = (board.hasTaxiNeighbor(current, s.getIdentifier()) && (p.getNumOfTaxis() > 0
                || p.getNumOfBoats() > 0)) || allowedToPlay;
        allowedToPlay = (board.hasBusNeighbor(current, s.getIdentifier()) && (p.getNumOfBuses() > 0
                || p.getNumOfBoats() > 0)) || allowedToPlay;
        allowedToPlay = (board.hasTrainNeighbor(current, s.getIdentifier()) && (p.getNumOfTrains() > 0
                || p.getNumOfBoats() > 0)) || allowedToPlay;
        allowedToPlay = (board.hasBoatNeighbor(current, s.getIdentifier()) && p.getNumOfBoats() > 0)
                || allowedToPlay;
        return allowedToPlay;
    }

    /**
     * checks if mister x has enough ticket to go to the given station
     *
     * @param board
     * @param station
     * @return
     */
    private boolean[] hasEnoughTicket(int station) {
        return board.checkIfNeihbor(station, this.getCurrStation(), this);
    }

    /**
     *
     * @param expectedStations
     * @param ticket
     * @return
     */
    @Override
    protected List<Station> expectedMrXStations(List<Station> expectedStations, Tickets ticket) {
        List<Station> temp = new LinkedList<>();

        if (ticket == Tickets.BOAT) {//boat tickt is a black ticket
            for (int i = 0; i < expectedStations.size(); i++) {
                for (int j = 0; j < Tickets.values().length; j++) {
                    addArrayToList(temp, expectedStations.get(i).getStationByType()[Tickets.values()[j].ordinal()]);
                }
            }
        } else {
            for (int i = 0; i < expectedStations.size(); i++) {
                addArrayToList(temp, expectedStations.get(i).getStationByType()[ticket.ordinal()]);
            }
        }

        return temp;
    }

    /**
     * this method is adding given array of station to the given list.TODO
     * change names
     *
     * @param temporaryList
     * @param neighbors
     */
    private void addArrayToList(List<Station> temporaryList, Station[] neighbors) {
        for (Station neighbor : neighbors) {//changed to another loop
            if (!temporaryList.contains(neighbor)) {
                temporaryList.add(neighbor);
            }
        }
    }
}