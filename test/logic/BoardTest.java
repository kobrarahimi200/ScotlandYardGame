/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.FileNotFoundException;
import java.util.List;
import org.json.JSONException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Reyhan
 */
public class BoardTest {

    public BoardTest() {
    }

    FakeGUI gui = new FakeGUI();

    @Test
    public void testGetBusNeighbor() throws JSONException, FileNotFoundException {
        Board board = new Board(gui);
        board.convertToArray();
//        assertArrayEquals(new int[]{10, 20}, m.get(1).getTaxiNeighbors());
//        assertArrayEquals(new int[]{4, 11, 12}, m.get(2).getTaxiNeighbors());
    }

    @Test
    public void testGetAllPositions() throws JSONException, FileNotFoundException {
        Board board = new Board(gui);
    }

    @Test
    public void testGetIdentifier() throws JSONException, FileNotFoundException {
        Board board = new Board(gui);
        assertEquals(1, board.getIdentifier(new Position(0.12210915818686401968, 0.04791154791154791232)));
        assertEquals(2, board.getIdentifier(new Position(0.30064754856614245293, 0.02948402948402948504)));
    }

    @Test
    public void testGetIdentifierMiddle() throws JSONException, FileNotFoundException {
        Board board = new Board(gui);
        assertEquals(36, board.getIdentifier(new Position(0.46345975948196116434, 0.24938574938574939455)));
    }

    @Test
    public void testGetIdentifierLast() throws JSONException, FileNotFoundException {
        Board board = new Board(gui);
        assertEquals(155, board.getIdentifier(new Position(0.47456059204440331767, 0.73587223587223582477)));
    }

    @Test
    public void testGetIdentifierLastest() throws JSONException, FileNotFoundException {
        Board board = new Board(gui);
        assertEquals(194, board.getIdentifier(new Position(0.32654949121184090322, 0.91154791154791159347)));
    }

    @Test
    public void testhasEnoughTicket() throws JSONException, FileNotFoundException {
        Board board = new Board(gui);
        Position pos = new Position(0.12210915818686401968, 0.04791154791154791232);
        assertTrue(board.hasEnoughTicket(2));
        assertTrue(board.hasEnoughTicket(3));
        assertTrue(board.hasEnoughTicket(67));
    }

    @Test
    public void testCehckIfNeihbor() throws JSONException, FileNotFoundException {
        Board board = new Board(gui);
        Position pos = new Position(0.12210915818686401968, 0.04791154791154791232);
        Player p = new Player(0, playerType.MISTERX, false, 3, 2, 1, 0);
        assertFalse(board.checkIfNeihbor(3, 1, p)[0]);//train
        assertFalse(board.checkIfNeihbor(8, 1, p)[1]);//with taxi want to go to id 8 
        assertTrue(board.checkIfNeihbor(46, 1, p)[1]);//with bus want to go to 46
        assertTrue(board.checkIfNeihbor(8, 1, p)[2]);//train
    }
}
