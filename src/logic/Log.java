package logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLDecoder;

/**
 * For traceability, one log file should be written per game. Some important
 * information are stored here such as, number of detectives, current position
 * of each player and previous position, remaining tickets, starting points, AI
 * value and so on.
 *
 * @author Kobra
 */
public class Log {

    FileOutputStream f;
    OutputStreamWriter o;
    String toprint = "";
    String startValues = "";
    Player[] players;
    String endGame = "";

    /**
     * default constructor
     *
     * @param s
     */
    public Log(String s) {
        try {
            URL url = IO.class.getProtectionDomain().getCodeSource().getLocation();
            String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
            String parentPath = new File(jarPath).getParentFile().getPath();
            this.f = new FileOutputStream(parentPath + "/" + s);
            this.o = new OutputStreamWriter(this.f);
        } catch (IOException ex) {

        }
    }

    /**
     * sets the default values
     *
     * @param players
     * @throws IOException
     */
    public void setDefaultsLogValues(Player[] players) throws IOException {
        this.players = players;
        startValues += players.length - 1 + ", " + players[0].isAI() + ", " + players[1].isAI() + "";
        for (int i = 0; i < players.length; i++) {
            startValues += ", " + players[i].getCurrStation();
        }
        startValues += "\n";
        o.append(startValues);

    }

    /**
     * logging all given parameters
     *
     *
     * @param players
     * @param idxCurrPlayer
     */
    public void logging(Player[] players, int idxCurrPlayer) {
        try {
            toprint = idxCurrPlayer + ", "
                    + players[idxCurrPlayer].getPrevStation() + ", "
                    + players[idxCurrPlayer].getCurrStation() + ", "
                    + players[idxCurrPlayer].getNumOfTrains() + ", "
                    + players[idxCurrPlayer].getNumOfBuses() + ", "
                    + players[idxCurrPlayer].getNumOfTaxis() + ", "
                    + players[idxCurrPlayer].getNumOfBoats() + ", "
                    + players[idxCurrPlayer].getaiID() + ", "
                    + players[idxCurrPlayer].getWeight() + "\n";

            o.append(toprint);
            o.flush();
        } catch (IOException ex) {

        }
    }

    /**
     * closing the log file
     */
    public void closeLog() {
        try {
            if (f != null) {
                f.close();
            }
            if (o != null) {
                o.close();
            }
        } catch (IOException ex) {

        }
    }
}
