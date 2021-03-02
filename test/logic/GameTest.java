/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.Player;

/**
 *
 * @author Reyhan
 */
public class GameTest {

    private final FakeGUI FakeGUI = new FakeGUI();

    public GameTest() {
    }

//    @Test
//    public void testGetRandom() throws JSONException, IOException {
//        Game game = new Game(1, 3, true, false, FakeGUI);
//        //game.startingPoints();
//        int x = game.selectRandom(game.startingPoints());
//        assertTrue(game.getPlayers()[game.getCurrPlayer()].isAI());
//    }

    @Test
    public void testChangeRound() throws JSONException, IOException {
        Game game = new Game(1, 3, true, false, FakeGUI);
        game.getPlayers()[0].setCurrStation(34);
        game.changeRound(Tickets.BUS, false);
        game.getPlayers()[1].setCurrStation(47);
        assertFalse(game.getIsGameWon());
    }

    @Test
    public void testWonGame() throws JSONException, FileNotFoundException {
        Game game = new Game(3, true, false, FakeGUI);
        game.getPlayers()[0].setCurrStation(47);
        game.getPlayers()[1].setCurrStation(47);
        game.getPlayers()[2].setCurrStation(112);
        game.getPlayers()[3].setCurrStation(53);
        game.endGame(game.getPlayers()[1].getPlayerType());
        assertTrue(game.getIsGameWon());
    }

    @Test
    public void testGetNumOfTicketsType() throws JSONException, FileNotFoundException {
        Game game = new Game(4, true, false, FakeGUI);
        assertTrue(game.hasEnoughTicket());
    }

    @Test
    public void testMovePlayer_MisterX() throws JSONException, IOException {
        Game game = new Game(1, 4, true, false, FakeGUI);
        Position pos = game.getBoard().getCoordinate(33);
        assertEquals(3, game.getPlayers()[0].getNumOfTaxis());
        game.setCurrPosition(pos);
        game.changeRound(Tickets.TAXI, false);
        assertEquals(4, game.getPlayers()[0].getNumOfTaxis());
       // assertEquals(34, game.getPlayers()[0].getCurrStation());
    }

    @Test
    public void testMovePlayer_DetectiveOne_setCuurPosition() throws JSONException, IOException {
        Game game = new Game(1, 4, true, false, FakeGUI);
        game.setCurrPlayer(1);
        Position pos = game.getBoard().getCoordinate(33);
        game.setCurrPosition(pos);
        game.changeRound(Tickets.TAXI, false);
        assertEquals(34, game.getPlayers()[1].getCurrStation());
    }

    @Test
    public void testMovePlayer_DetectiveOne_UseBusTicket() throws JSONException, IOException {
        Game game = new Game(1, 4, true, false, FakeGUI);
        game.setCurrPlayer(1);
        assertEquals(8, game.getPlayers()[1].getNumOfBuses());
        game.changeRound(Tickets.BUS, false);
        assertEquals(7, game.getPlayers()[1].getNumOfBuses());
    }

    @Test
    public void testMovePlayer_DetectiveTwo_getAllTickets() throws JSONException, IOException {
        Game game = new Game(1, 3, true, false, FakeGUI);
        game.setCurrPlayer(2);
        assertEquals(8, game.getPlayers()[2].getNumOfBuses());
        game.changeRound(Tickets.BUS, false);
        assertEquals(7, game.getPlayers()[2].getNumOfBuses());
        assertArrayEquals(new int[]{4, 7, 10, 0}, game.getPlayers()[2].getAllTickets());

    }

    @Test
    public void testGetAllPlayers() throws JSONException, IOException {
        Game game = new Game(1, 5, true, false, FakeGUI);
        assertEquals(6, game.getPlayers().length);
    }

    @Test
    public void testMovePlayer_DetectiveThree_getRemainigTicket() throws JSONException, IOException {
        Game game = new Game(1, 3, true, false, FakeGUI);
        game.setCurrPlayer(2);
        assertEquals(8, game.getPlayers()[2].getNumOfBuses());
        game.changeRound(Tickets.BUS, false);
        assertEquals(7, game.getPlayers()[2].getNumOfBuses());
        game.changeRound(Tickets.TAXI, false);
       // assertArrayEquals(new int[]{4, 8,10, 0}, game.getPlayers()[3].getAllTickets());

    }

    @Test
    public void testDetectiveThreeIsWinner() throws JSONException, FileNotFoundException {
        Game game = new Game(3, true, false, FakeGUI);
        game.getPlayers()[0].setCurrStation(91);
        game.getPlayers()[1].setCurrStation(47);
        game.getPlayers()[2].setCurrStation(112);
        game.getPlayers()[3].setCurrStation(53);
        game.setCurrPlayer(3);
        Position pos = game.getBoard().getCoordinate(90);
        game.setCurrPosition(pos);
        game.changeRound(Tickets.TAXI, false);
        assertTrue(game.getIsGameWon());
        System.out.println("curr pos 0 : " + game.getPlayers()[0].getCurrStation());//91
    }
}
