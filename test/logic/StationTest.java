/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.IOException;
import org.json.JSONException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Reyhan
 */
public class StationTest {

    public StationTest() {
    }

    @Test
    public void testSomeMethod() throws JSONException, IOException {
        Game g = new Game(1, 3, true, true, new FakeGUI());
        System.out.println("staion test" + g.getBoard().getAllStations().get(3));
    }

}
