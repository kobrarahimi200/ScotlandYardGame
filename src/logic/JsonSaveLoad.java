package logic;

/**
 * this class is responsible for setting the save object attributes and also
 * getting the loaded file.
 *
 * @author Reyhan
 */
public class JsonSaveLoad {

    private SaveLoadMisterX misterX;
    private SaveLoadDetective detectives;
    private int whosTurn;
    private int currRoundNo;
    private boolean gameIsWon;

    /**
     * gets the object of saveloadmisterx
     *
     * @return
     */
    public SaveLoadMisterX getMisterX() {
        return misterX;
    }

    /**
     * sets the given object
     *
     * @param misterX
     */
    public void setMisterX(SaveLoadMisterX misterX) {
        this.misterX = misterX;
    }

    /**
     * gets tge the detective
     *
     * @return
     */
    public SaveLoadDetective getDetectives() {
        return detectives;
    }

    /**
     * sets the given detectives
     *
     * @param detectives
     */
    public void setDetectives(SaveLoadDetective detectives) {
        this.detectives = detectives;
    }

    /**
     * gets the whoseturn number
     *
     * @return
     */
    public int getWhosTurn() {
        return whosTurn;
    }

    /**
     * sets the given number to whoseturn
     *
     * @param whosTurn
     */
    public void setWhosTurn(int whosTurn) {
        this.whosTurn = whosTurn;
    }

    /**
     * gets the current round number
     *
     * @return
     */
    public int getCurrRoundNo() {
        return currRoundNo;
    }

    /**
     * sets the current round number
     *
     * @param currRoundNo
     */
    public void setCurrRoundNo(int currRoundNo) {
        this.currRoundNo = currRoundNo;
    }

    /**
     * gets the boolean value of isWonGame
     *
     * @return
     */
    public boolean isGameIsWon() {
        return gameIsWon;
    }

    /**
     * sets the given boolean value to isGameWon
     *
     * @param gameIsWon
     */
    public void setGameIsWon(boolean gameIsWon) {
        this.gameIsWon = gameIsWon;
    }

    /**
     * create an object for save and load for mister x with given attributes
     */
    public static class SaveLoadMisterX {

        private boolean ai;
        private int[] possibleTargets;
        private int lastShownPos;
        private int currPos;
        private int[] remainingTickets;
        private int[] journeyBoard;

        /**
         * gets the boolean value isAI
         *
         * @return
         */
        public boolean isAI() {
            return ai;
        }

        /**
         * sets the boolean value
         *
         * @param ai
         */
        public void setAI(boolean ai) {
            this.ai = ai;
        }

        /**
         * gets an array of the possibole target
         *
         * @return
         */
        public int[] getPossibleTargets() {
            return possibleTargets;
        }

        /**
         * set the given array to the possible target array
         *
         * @param possibleTargets
         */
        public void setPossibleTargets(int[] possibleTargets) {
            this.possibleTargets = possibleTargets;
        }

        /**
         * gets the last shown position value
         *
         * @return
         */
        public int getLastShownPos() {
            return lastShownPos;
        }

        /**
         * sets the given value to the lastshown position value
         *
         * @param lastShownPos
         */
        public void setLastShownPos(int lastShownPos) {
            this.lastShownPos = lastShownPos;
        }

        /**
         * gets the cuurent position
         *
         * @return
         */
        public int getCurrPos() {
            return currPos;
        }

        /**
         * sets the current position
         *
         * @param currPos
         */
        public void setCurrPos(int currPos) {
            this.currPos = currPos;
        }

        /**
         * gets an array of remaining tickets
         *
         * @return
         */
        public int[] getRemainingTickets() {
            return remainingTickets;
        }

        /**
         * sets the remaining ticket array
         *
         * @param remainingTickets
         */
        public void setRemainingTickets(int[] remainingTickets) {
            this.remainingTickets = remainingTickets;
        }

        /**
         * gets the journey board
         *
         * @return
         */
        public int[] getJourneyBoard() {
            return journeyBoard;
        }

        /**
         * sets the journey board
         *
         * @param journeyBoard
         */
        public void setJourneyBoard(int[] journeyBoard) {
            this.journeyBoard = journeyBoard;
        }

        /**
         * default constructor
         */
        public SaveLoadMisterX() {
        }
    }

    /**
     * create an object for detectives with the given attributes
     */
    public static class SaveLoadDetective {

        private int noOfDetectives;
        private boolean ai;
        private SLDetectives players[];

        /**
         * default constructor
         */
        public SaveLoadDetective() {
        }

        /**
         * gets the number of detectives
         *
         * @return
         */
        public int getNoOfDetectives() {
            return noOfDetectives;
        }

        /**
         * sets the number of detectives
         *
         * @param noOfDetectives
         */
        public void setNoOfDetectives(int noOfDetectives) {
            this.noOfDetectives = noOfDetectives;
        }

        /**
         * gets the booelan value of isAI
         *
         * @return
         */
        public boolean isAi() {
            return ai;
        }

        /**
         * sets the isAI
         *
         * @param ai
         */
        public void setAi(boolean ai) {
            this.ai = ai;
        }

        /**
         * gets the artay of players
         *
         * @return
         */
        public SLDetectives[] getPlayers() {
            return players;
        }

        /**
         * sets the player array
         *
         * @param players
         */
        public void setPlayers(SLDetectives[] players) {
            this.players = players;
        }

    }

    /**
     * create an object for all detectives with the required attributes
     */
    public static class SLDetectives {

        private int position;
        private int remainingTickets[];

        /**
         * default constructor
         */
        public SLDetectives() {
        }

        /**
         * gets the identifier
         *
         * @return
         */
        public int getPos() {
            return position;
        }

        /**
         * sets the identifier
         *
         * @param position
         */
        public void setPos(int position) {
            this.position = position;
        }

        /**
         * gets the remainingticket array
         *
         * @return
         */
        public int[] getRemainingTickets() {
            return remainingTickets;
        }

        /**
         * sets the remaining ticket array
         *
         * @param remainingTickets
         */
        public void setRemainingTickets(int[] remainingTickets) {
            this.remainingTickets = remainingTickets;
        }

    }
}
