/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import static logic.playerType.BLACK;
import static logic.playerType.BLUE;
import static logic.playerType.GREEN;
import static logic.playerType.MISTERX;
import static logic.playerType.RED;
import static logic.playerType.YELLOW;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Reyhan
 */
public class PlayerTest {

    @Test
    public void testcreatePlayerArray_getNumOfTickets() {
        Player[] players = new Player[4];
        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new MisterX(i, playerType.MISTERX, true, 3, 3, 3, 0);
            } else {
                players[i] = new Detectives(i, playerType.values()[i], false, 4, 4, 4, 0);
            }
        }
        assertEquals(4, players.length);
        assertEquals(MISTERX, playerType.values()[0]);
        assertEquals(BLUE, playerType.values()[1]);
        assertEquals(YELLOW, playerType.values()[2]);
        assertEquals(RED, playerType.values()[3]);
        assertEquals(GREEN, playerType.values()[4]);
        assertEquals(BLACK, playerType.values()[5]);
    }

    @Test
    public void testgetNumOfTickets() {
        Player[] players = new Player[5];
        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new MisterX(i, playerType.MISTERX, true, 3, 3, 3, 0);
            } else {
                players[i] = new Detectives(i, playerType.values()[i], false, 4, 4, 4, 0);
            }
        }
        assertEquals(5, players.length);
        assertArrayEquals(new int[]{3, 3, 3, 0}, players[0].getAllTickets());
        assertArrayEquals(new int[]{4, 4, 4, 0}, players[1].getAllTickets());
        assertEquals(BLUE, playerType.values()[1]);
        assertEquals(YELLOW, playerType.values()[2]);
        assertEquals(RED, playerType.values()[3]);
        assertEquals(GREEN, playerType.values()[4]);
        assertEquals(BLACK, playerType.values()[5]);
    }

    @Test
    public void testgetNumOfBusTicket() {
        Player[] players = new Player[5];
        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new MisterX(i, playerType.MISTERX, true, 3, 3, 3, 0);
            } else {
                players[i] = new Detectives(i, playerType.values()[i], false, 4, 4, 4, 0);
            }
        }
        assertEquals(5, players.length);
        assertEquals(3, players[0].getNumOfBuses());
        assertEquals(4, players[1].getNumOfBuses());
    }

    @Test
    public void testChecksIfIsAi() {
        Player[] players = new Player[5];
        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new MisterX(i, playerType.MISTERX, true, 3, 3, 3, 0);
            } else {
                players[i] = new Detectives(i, playerType.values()[i], false, 4, 4, 4, 0);
            }
        }
        assertEquals(5, players.length);
        assertTrue(players[0].isAI());
        assertFalse(players[1].isAI());
    }

}
