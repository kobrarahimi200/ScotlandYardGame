/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;

/**
 *
 * @author Reyhan
 */
public class FakeGUI implements GUIConnector {

    @Override
    public void clear() {
    }

    @Override
    public void showCurrentPlayer(int player) {
    }

    @Override
    public void displaySymbol(Position currPosition, int idxCurrPlayer, boolean s) {
    }

    public void showInDropDownList(Game game, boolean[] listOfTicket, Position pos) {
    }

    @Override
    public void updateLabelTicketsNum(int[] numOfTickets) {

    }

    @Override
    public void gameWon(String winnerName, int idPlayer) {

    }

    @Override
    public void showMisterXTicket(String ticket) {

    }

    @Override
    public void showInDropDownList(Game game, boolean[] listOfTicket, Position pos, boolean blackTicket) {
    }

    @Override
    public void disableBlackTicketCheckBox(boolean isDisable) {
    }

    @Override
    public void showMsg(int x, int id) {
    }

    @Override
    public void hideMisterX() {
    }

}
