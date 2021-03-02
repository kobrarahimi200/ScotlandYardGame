package logic;

/**
 * communicate logic with gui. These all methods here are required to display
 * the status of the game.
 *
 * @author kobra
 */
public interface GUIConnector {

    /**
     * clear the board
     */
    public void clear();

    /**
     * display the id of current player
     *
     * @param player
     */
    public void showCurrentPlayer(int player);

    /**
     * show the drop down list of all availavle ticket for a sation
     *
     * @param game
     * @param listOfTicket
     * @param pos
     * @param blackTicket
     */
    public void showInDropDownList(Game game, boolean[] listOfTicket, Position pos, boolean blackTicket);

    /**
     * display the symbol of the player in the given station
     *
     * @param currPosition
     * @param idxCurrPlayer
     * @param is
     */
    public void displaySymbol(Position currPosition, int idxCurrPlayer, boolean is);

    /**
     * update the number of tickets
     *
     * @param numOfTickets
     */
    public void updateLabelTicketsNum(int[] numOfTickets);

    /**
     * show the alert box with the name of winner
     *
     * @param winnerName
     * @param idPlayer
     */
    public void gameWon(String winnerName, int idPlayer);

    /**
     * displays the mister x tickets in the travel log display the mister x
     * tickets
     *
     * @param ticket
     */
    public void showMisterXTicket(String ticket);

    /**
     * shows the error when a json file is readed show error messages in the gui
     *
     * @param x
     */
    public void showMsg(int x, int id);

    /**
     * disable the blackTicket check box when the current player is detective
     *
     * @param isDisable
     */
    public void disableBlackTicketCheckBox(boolean isDisable);

    /**
     * hide misterx symbol
     */
    public void hideMisterX();
}
