package logic;

import java.io.IOException;
import org.json.JSONException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * is testing the methods inside the IO class like saving and loading the game
 *
 * @author Reyhan
 */
public class IOTest {

    private final FakeGUI FakeGUI = new FakeGUI();
/**
 * this test is not working because of the gui
 * @throws JSONException
 * @throws IOException 
 */
    @Test
    public void testLoadFromSavedObject() throws JSONException, IOException {
        IO io = new IO(FakeGUI);
        Player[] p = new Player[4];
        for (int i = 0; i < 1; i++) {
            p[i] = new MisterX(i, playerType.MISTERX, false, 4, 3, 4, 3);
        }
        for (int k = 1; k < 3 + 1; k++) {
            p[k] = new Detectives(k, playerType.values()[k], false, 4, 4, 4, 0);
        }
        Game game = new Game(new Board(FakeGUI), FakeGUI, p, 3, 4, true);
        game.load(game);
        assertEquals(2, game.getCurrPlayer());
    }

    @Test
    public void testLoadGame_getNumofPlayers() throws JSONException, IOException {
        IO io = new IO(FakeGUI);
        Player[] p = new Player[4];
        for (int i = 0; i < 1; i++) {
            p[i] = new MisterX(i, playerType.MISTERX, false, 4, 3, 4, 3);
        }
        for (int k = 1; k < 3 + 1; k++) {
            p[k] = new Detectives(k, playerType.values()[k], false, 4, 4, 4, 0);
        }
        Game game = new Game(new Board(FakeGUI), FakeGUI, p, 0, 0, true);
        game.load(game);
        assertEquals(4, game.getPlayers().length);
    }

}
