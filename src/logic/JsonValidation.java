/**
 * JSON Schema is a declarative language for validating the format and structure
 * of a JSON Object. It allows us to specify the number of special primitives to
 * describe exactly what a valid JSON Object will look like.
 */
package logic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * validate the given json file value if they have erro return a number and send
 * it ot the gui to display the error message
 *
 * @author kobra
 */
public class JsonValidation {

    private int errorNumber;
    private int identifierError;
    JSONArray stations = null;
    JSONObject jsonObj = null;
    JSONObject keys = null;
    private int errorLoadNum = 0;
    JSONObject json;

    /**
     * main constructor
     *
     * @throws JSONException
     */
    public JsonValidation() throws JSONException {
        errorNumber = 0;
        identifierError = 1;
    }

    /**
     * second constructor
     *
     * @param stations
     */
    JsonValidation(Stations[] stations) {
        assert stations != null;
        errorNumber = validationOfMap(stations);

    }

    /**
     * gets the error number
     *
     * @return
     */
    int getErrorNumber() {
        return this.errorNumber;
    }

    /**
     * gets the id of error number
     *
     * @return
     */
    int getIDErrorNumber() {
        return this.identifierError;
    }

    /**
     * return a number corresponding the type of error in the net.sjon file
     *
     * @param stations
     * @return
     */
    int validationOfMap(Stations[] stations) {

        boolean hasError = false;
        if (stations.length != 199) {
            hasError = true;
            errorLoadNum = 1;
            identifierError = 1;

        }
        for (int i = 0; i < stations.length && !hasError; i++) {
            if (stations[i].getIdentifier() < 1 || stations[i].getIdentifier() > 199) {
                hasError = true;
                errorLoadNum = 2;
                identifierError = i;
            }
            if (!hasError && stations[i].getPosition().x() < 0.0 || stations[i].getPosition().x() > 1.0
                    || stations[i].getPosition().y() < 0.0 || stations[i].getPosition().y() > 1.0) {
                hasError = true;
                errorLoadNum = 3;
                identifierError = i;
            }
            for (int j = 0; j < stations[i].getTube().length && !hasError; j++) {
                if (stations[i].getTube()[j] < 1 || stations[i].getTube()[j] > 199) {
                    hasError = true;
                    errorLoadNum = 4;
                    identifierError = i;
                }
            }
            for (int j = 0; j < stations[i].getBus().length && !hasError; j++) {
                if (stations[i].getBus()[j] < 1 || stations[i].getBus()[j] > 199) {
                    hasError = true;
                    errorLoadNum = 5;
                    identifierError = i;
                }
            }
            for (int j = 0; j < stations[i].getCab().length && !hasError; j++) {
                if (stations[i].getCab()[j] < 1 || stations[i].getCab()[j] > 199) {
                    hasError = true;
                    errorLoadNum = 6;
                    identifierError = i;
                }
            }
            for (int j = 0; j < stations[i].getBoat().length && !hasError; j++) {
                if (stations[i].getBoat()[j] < 1 || stations[i].getBoat()[j] > 199) {
                    hasError = true;
                    errorLoadNum = 7;
                    identifierError = i;
                }
            }
        }

        return errorLoadNum;
    }

    /**
     * return a number corresponding to the loaded file erro
     *
     * @param gui
     * @param obj
     * @return
     */
    protected int loadErrorHandling(GUIConnector gui, JsonSaveLoad obj) {
        boolean loadError = false;
        if (obj.getDetectives().getPlayers().length < 3
                || obj.getDetectives().getPlayers().length > 5) {
            loadError = true;
            errorLoadNum = 8;
        }
        if (!loadError && (obj.getDetectives().getNoOfDetectives() < 3
                || obj.getDetectives().getNoOfDetectives() > 5)) {
            loadError = true;
            errorLoadNum = 9;
        }
        if (!loadError && obj.getDetectives().getNoOfDetectives()
                != obj.getDetectives().getPlayers().length) {
            loadError = true;
            errorLoadNum = 10;
        }
        if (!loadError && (obj.getMisterX().getCurrPos() < 1
                || obj.getMisterX().getCurrPos() > 199)) {
            loadError = true;
            errorLoadNum = 11;
        }
        if (!loadError) {
            for (int i = 0; i < obj.getDetectives().getPlayers().length
                    && !loadError; i++) {
                if (obj.getDetectives().getPlayers()[i].getPos() < 1
                        || obj.getDetectives().getPlayers()[i].getPos() > 199) {
                    loadError = true;
                    errorLoadNum = 12;
                    identifierError = i;
                }
            }
        }
        if (!loadError) {
            for (int i = 0; i < obj.getDetectives().getPlayers().length
                    && !loadError; i++) {
                if (obj.getDetectives().getPlayers()[i] == null) {
                    loadError = true;
                    errorLoadNum = 13;
                }
            }
        }

        if (!loadError && (obj.getMisterX().getLastShownPos() < 1
                || obj.getMisterX().getLastShownPos() > 199)) {
            loadError = true;
            errorLoadNum = 14;
        }

        if (!loadError && (obj.getWhosTurn() < 0 || obj.getWhosTurn()
                > (obj.getDetectives().getNoOfDetectives()))) {
            loadError = true;
            errorLoadNum = 15;
        }
        if (!loadError && (obj.getCurrRoundNo() < 0 || obj.getCurrRoundNo()
                > 23)) {
            loadError = true;
            errorLoadNum = 16;
        }
        return errorLoadNum;
    }

}
