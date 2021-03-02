package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * this class is implemented for another type of players (Detectives) with all
 * required methods. This also calculate the four stategy for the selecting the
 * best path according to sgortest path algorithm.
 *
 * @author kobra
 */
public class Detectives extends Player {

    // List<Station> neighborsDetective;
    private Choose choose;
    Player[] players;//detectives
    Board board;//board of the players

    /**
     * create detectives with the given parameters
     *
     * @param id
     * @param type
     * @param isAI
     * @param numOfTrains
     * @param numOfBuses
     * @param numOfTaxis
     * @param numOfBoats
     */
    public Detectives(int id, playerType type, boolean isAI, int numOfTrains, int numOfBuses, int numOfTaxis,
            int numOfBoats) {
        super(id, type, isAI, numOfTrains, numOfBuses, numOfTaxis, numOfBoats);
        choose = null;
    }

    @Override
    protected Choose getChoosedAI() {
        return this.choose;
    }

    /**
     * This method is responsible for the AI of detectives and checks every
     * strategy one by one, finally select the best route. in the first
     * strategy, find the all target position with current player. in the
     * second, check if the current player is the neighbor with train station.
     * third, How many stations can be reached from the current position with
     * the existing tickets in one turn. Forth,find the neighbor with smallest
     * id and move to that.
     *
     * @param players
     * @param board
     * @param targetPositions
     */
    @Override
    void AI(Board board, Player[] players, List<Station> targetPositions) {

        Choose choose = null;
        Choose temp = null;
        this.players = players;
        this.board = board;
        // first strategy
        List<Station> targetsNeigbors = isNeighborWithTargets(targetPositions);
        Station stFirst = stationWithSmallestId(targetsNeigbors);
        if (!targetsNeigbors.isEmpty() && stFirst != null) {
            //Station station = stFirst;
            temp = weightCalc(targetPositions, stFirst);
            choose = checksIfWeightNotNull(choose, temp);
        }
        // second strategy
        if (trainNeighborStation() != null) {
            temp = weightCalc(targetPositions, trainNeighborStation());
            choose = checksIfWeightNotNull(choose, temp);
        }
        // third strategy
        List<Station> path = bestPathToDestination(closestToAverageOfStations(targetPositions));
        if (!path.isEmpty()) {
            temp = weightCalc(targetPositions, path.get(0));
            choose = checksIfWeightNotNull(choose, temp);
        }
        // forth strategy
        Station station = stationWithSmallestId(board.getStation(this.getCurrStation()).getAllNeighbors());
        if (station != null) {
            temp = weightCalc(targetPositions, station);
            choose = checksIfWeightNotNull(choose, temp);
        }
        this.choose = choose;
    }

    /**
     * checks if the given choose is not null, and if they have weight then temp
     * is copied to choose parameter. This method is used for AI
     *
     * @param choose
     * @param temp
     * @return
     */
    private Choose checksIfWeightNotNull(Choose choose, Choose temp) {
        if (choose == null) {
            choose = temp;
        } else {
            if (temp != null && temp.getWeight() > choose.getWeight()) {
                choose = temp;
            }
        }
        return choose;
    }

    /**
     * return and checks How many stations can be reached from the current
     * position with the existing tickets in one turn
     *
     * @param station
     * @return
     */
    protected List<Station> bestPathToDestination(Station station) {
        Board br = this.board;
        List<Station> neighbors = br.getStation(this.getCurrStation()).getAllNeighbors();
        List<Station> bestRoute = new LinkedList<>();

        for (int i = 0; i < neighbors.size(); i++) {
            if (!isFullByDetectives(neighbors.get(i))
                    && ableToGoToStation(br.getStation(this.getCurrStation()), neighbors.get(i))) {
                List<Station> path = getBestRoutes(neighbors.get(i), station);

                if (bestRoute.isEmpty()) {
                    bestRoute = path;
                } else {
                    if (path.size() < bestRoute.size() || (path.size() == bestRoute.size()
                            && path.get(0).getIdentifier() < bestRoute.get(0).getIdentifier())) {
                        bestRoute = path;
                    }
                }
            }
        }

        return bestRoute;
    }

    /**
     * this method is check if any of stations in given list is neighbor with
     * current station of the player and add it to temp list. otherwise an empty
     * list will be returned
     *
     * @param stations
     * @return List of station neighboring with current station of the player
     */
    private List<Station> isNeighborWithTargets(List<Station> stations) {
        List<Station> temp = new LinkedList<>();
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).isNeighbor(this.getCurrStation())) {
                temp.add(stations.get(i));
            }
        }
        return temp;
    }

    /**
     * checks if the targetstations contains the given station then remove it.
     * fins the best route from the given station to the closest station among
     * target stations.
     *
     * @param targetStations
     * @param station
     * @return
     */
    protected Choose weightCalc(List<Station> targetStations, Station station) {
        double weight = 0.0;
        double sum = 0.0;
        double temporary = 0.0;
        Choose choose = null;

        List<Station> path = getBestRoutes(station,
                closestToAverageOfStations(targetStations));
        if (targetStations.contains(station)) {
            targetStations.remove(station);
        }
        if (targetStations.size() > 0) {
            weight = weight + (neighborsWithStations(targetStations, station)
                    / (targetStations.size()) * 10);
        }
        if (path.size() > 1 && path.size() < 11) {
            weight = weight + (10.0 - path.size() - 1);
        }
        boolean[] moreRoute = station.HasMoreNeighbors(this.getCurrStation());
        for (int i = 0; i < moreRoute.length; i++) {
            temporary = 0.0;
            if (moreRoute[i] && this.getTicketType(Tickets.values()[i]) > 0) {
                Tickets ticket = Tickets.values()[i];
                this.decTicket(ticket);
                temporary = temporary + numberOfAccessibleStations(station) / 13 * 4;
                temporary = temporary + ((this.getMinTicket() > 2) ? 3 : this.getMinTicket());
                if (weight + temporary > sum) {
                    sum = weight + temporary;
                    choose = new Choose(ticket, station, sum);
                    this.setWeight(sum);
                }
                this.incTicket(ticket);
            }
        }
        return choose;
    }

    /**
     * this method finds the station with smallest id from the given list of
     * stations.
     *
     * @param stations
     * @return station with smallest id
     */
    private Station stationWithSmallestId(List<Station> stations) {
        Station st = null;
        for (int i = 0; i < stations.size(); i++) {
            if (!isFullByDetectives(stations.get(i))
                    && ableToGoToStation(board.getStation(this.getCurrStation()),
                            stations.get(i))) {
                if (st == null) {
                    st = stations.get(i);
                } else {
                    if (stations.get(i).getIdentifier() < st.getIdentifier()) {
                        st = stations.get(i);
                    }
                }
            }
        }

        return st;
    }

    /**
     * this method checks if the player is neighboring with a train station and
     * that station is free. if there are more than one train stations the
     * smallest one will be chosen
     *
     * @param players
     * @param board
     * @return station of train with smallest id and if is free.
     */
    private Station trainNeighborStation() {
        Station st = null;

        List<Station> stations = board.getStation(this.getCurrStation()).getAllNeighbors();
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getTrainNeighbors().length > 0
                    && !isFullByDetectives(stations.get(i))
                    && ableToGoToStation(board.getStation(this.getCurrStation()),
                            stations.get(i))) {
                if (st == null) {
                    st = stations.get(i);
                } else {
                    if (stations.get(i).getIdentifier() < st.getIdentifier()) {
                        st = stations.get(i);
                    }
                }
            }
        }

        return st;
    }

    /**
     * checks if the given station has route to any of the stations of the given
     * list then the neighbors is increased by one..
     *
     * @param stations
     * @param st
     * @return integer number stations which are neighbors of given station
     */
    private int neighborsWithStations(List<Station> stations, Station st) {
        assert (st != null);
        int neighbors = 0;
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).isNeighbor(st.getIdentifier())) {
                neighbors++;
            }
        }
        return neighbors;
    }

    /**
     * this method is averaging the x and y from Xs and Ys of all stations of
     * the targetedStations list and then find the closed station to this
     * average x and y
     *
     * @param board
     * @param target
     * @return closest station to average x and y
     */
    private Station closestToAverageOfStations(List<Station> target) {

        double x = 0.0;
        double y = 0.0;
        for (int i = 0; i < target.size(); i++) {
            x = x + target.get(i).getPos().x();
            y = y + target.get(i).getPos().y();
        }
        return board.closestStationToPosition(new Position(x / (double) target.size(), y
                / (double) target.size()));
    }

    /**
     * This method is checking if the given station is occupied by a detective
     *
     * @param players
     * @param st
     * @return true if occupied
     */
    private boolean isFullByDetectives(Station st) {
        boolean full = false;
        for (int i = 1; i < players.length && !full; i++) {
            if (players[i].getCurrStation() == st.getIdentifier()) {
                full = true;
            }
        }
        return full;
    }

    /**
     * this method is checking if the given player can move to the given
     * station. it checks if the player's current station is a neighbor of given
     * station and player can move to the station with its available ticket.
     *
     * @param p
     * @param st
     * @param br
     * @return true if player can move to given station
     */
    private boolean ableToGoToStation(Station curr, Station st) {
        boolean ableToMove = false;
        ableToMove = ableToMove || (board.hasTaxiNeighbor(curr.getIdentifier(), st.getIdentifier())
                && this.getNumOfTaxis() > 0);

        ableToMove = ableToMove || (board.hasBusNeighbor(curr.getIdentifier(), st.getIdentifier())
                && this.getNumOfBuses() > 0);

        ableToMove = ableToMove || (board.hasTrainNeighbor(curr.getIdentifier(), st.getIdentifier())
                && this.getNumOfTrains() > 0);

        ableToMove = ableToMove || (board.hasBoatNeighbor(curr.getIdentifier(), st.getIdentifier())
                && this.getNumOfBoats() > 0);
        return ableToMove;
    }

    /**
     * checks the available stations and count them. the player should have
     * ticket to go.
     *
     * @param st
     * @param br
     * @return double number of available stations
     */
    private double numberOfAccessibleStations(Station st) {
        boolean deleted = false;
        List<Station> neighbors = st.getAllNeighbors();
        for (int j = 0; j < neighbors.size() && !deleted; j++) {
            if (!ableToGoToStation(st, neighbors.get(j))) {
                neighbors.remove(j);
                j--;
                deleted = true;
            }
        }
        return neighbors.size();
    }

    /**
     * find the best route among all possible moves with the shortest path
     * algorithm.
     *
     *
     * resource(https://www.geeksforgeeks.org/dijkstras-shortest-path
     * -algorithm-greedy-algo-7/)
     *
     * @param from
     * @param to
     * @return
     */
    List<Station> getBestRoutes(Station from, Station to) {

        LinkedList<List<Station>> routesQueue = new LinkedList<>();
        Map<Station, Integer> visited = new HashMap<>();
        visited.put(from, 0);
        List<Station> firstRoute = new ArrayList<>();
        firstRoute.add(from);
        routesQueue.add(firstRoute);
        List<Station> bestRoute = new LinkedList<>();
        while (!routesQueue.isEmpty()) {
            List<Station> route = routesQueue.remove();
            Station lastStation = route.get(route.size() - 1);
            if (lastStation.equals(to)) {
                if (bestRoute.isEmpty()) {
                    bestRoute = route;
                } else {
                    if (route.size() < bestRoute.size() || (route.size() == bestRoute.size()
                            && route.get(1).getIdentifier() < bestRoute.get(1).getIdentifier())) {
                        bestRoute = route;
                    }
                }
            }
            for (int i = 0; i < lastStation.getAllNeighbors().size(); i++) {
                Station child = lastStation.getAllNeighbors().get(i);
                if (!isFullByDetectives(child)
                        && (!visited.containsKey(child) || visited.get(child) == route.size())) {
                    List<Station> newRoute = new ArrayList<>();
                    newRoute.addAll(route);
                    newRoute.add(child);
                    visited.put(child, route.size());
                    routesQueue.add(newRoute);
                }
            }
        }

        return bestRoute;
    }

}
