/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Reyhan
 */
public class MisterXTest {

    public MisterXTest() {
    }

    @Test
    public void testGetMisterXNeigbors_7() throws JSONException, FileNotFoundException {
        List<Integer> temp = new LinkedList<>();
        temp.add(6);
        temp.add(17);
        temp.add(42);
        Game game = new Game(3, true, true, new FakeGUI());

        game.getPlayers()[0].setCurrStation(7);
        game.getPlayers()[1].setCurrStation(72);
        game.getPlayers()[2].setCurrStation(30);
        game.getPlayers()[3].setCurrStation(88);

        assertEquals(temp.get(0).intValue(), 
                game.getPlayers()[0].getAllFreeNeighbors(game.getBoard(),game.getPlayers()).get(0).getIdentifier());
        game.getPlayers()[0].AI(game.getBoard(), game.getPlayers(), new LinkedList<Station>());

    }

    @Test
    public void testGetMisterXNeigbors() throws JSONException, FileNotFoundException {
        List<Integer> temp = new LinkedList<>();
        temp.add(4);
        temp.add(46);
        temp.add(67);
        temp.add(89);
        temp.add(14);
        temp.add(23);
        temp.add(24);

        Game game = new Game(3, true, true, new FakeGUI());

        game.getPlayers()[0].setCurrStation(13);
        game.getPlayers()[1].setCurrStation(23);
        game.getPlayers()[2].setCurrStation(74);
        game.getPlayers()[3].setCurrStation(88);

        assertEquals(temp.get(0).intValue(), game.getPlayers()[0].getAllFreeNeighbors(game.getBoard(),
                game.getPlayers()).get(0).getIdentifier());
        game.getPlayers()[0].AI(game.getBoard(), game.getPlayers(), new LinkedList<Station>());

    }

    @Test
    public void testGetMisterXNeigbors_184() throws JSONException, IOException {
        List<Integer> temp = new LinkedList<>();//153,156,180,185,168,169,185,196,197
        temp.add(153);
        temp.add(156);
        temp.add(180);
        Game game = new Game(1, 3, true, true, new FakeGUI());
        //MisterX mister =new MisterX(0, playerType.MISTERX, true, 2 , 2, 2, 1);
        game.getPlayers()[0].setCurrStation(184);

        assertEquals(temp.get(0).intValue(), game.getPlayers()[0].getAllFreeNeighbors(game.getBoard(),
                game.getPlayers()).get(0).getIdentifier());
        assertEquals(temp.get(1).intValue(), game.getPlayers()[0].getAllFreeNeighbors(game.getBoard(),
                game.getPlayers()).get(1).getIdentifier());
     
    }

    @Test
    public void testGetMisterXNeigbors_168() throws JSONException, IOException {
        List<Integer> temp = new LinkedList<>();//153,156,180,185,168,169,185,196,197
        temp.add(153);
        temp.add(156);
        temp.add(180);
        Game game = new Game(1, 3, true, true, new FakeGUI());
        //MisterX mister =new MisterX(0, playerType.MISTERX, true, 2 , 2, 2, 1);
        game.getPlayers()[0].setCurrStation(184);

        assertEquals(temp.get(0).intValue(), game.getPlayers()[0].getAllFreeNeighbors(game.getBoard(),
                game.getPlayers()).get(0).getIdentifier());
        assertEquals(temp.get(1).intValue(), game.getPlayers()[0].getAllFreeNeighbors(game.getBoard(),
                game.getPlayers()).get(1).getIdentifier());
        assertEquals(temp.get(2).intValue(), 180);
    }

}
