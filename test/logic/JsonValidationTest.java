/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.json.JSONException;

/**
 *
 * @author Alireza
 */
public class JsonValidationTest {

    IO Io;
    FakeGUI gui = new FakeGUI();

    @Test
    public void checkIfHasStation() throws JSONException, FileNotFoundException {
        Io = new IO(gui);
        Object obj = Io.getMapJson();
        JsonValidation jsonValidation = new JsonValidation(Io.getJsonValues(gui));
        assertEquals(0, jsonValidation.getErrorNumber());
    }

    @Test
    public void checkIfIdentifierIsValid() throws JSONException, FileNotFoundException {
        Io = new IO(gui);
        Object obj = Io.getMapJson();
        JsonValidation jsonValidation = new JsonValidation(Io.getJsonValues(gui));
        assertEquals(0, jsonValidation.getErrorNumber());
    }

   

    @Test
    public void TestLoadFileValidation_misterX() throws JSONException, FileNotFoundException {
        Io = new IO(gui);
        Object obj = new Object();
//        JsonValidation jsonValidation = new JsonValidation(Io.getJsonValues());
//        assertEquals(0, jsonValidation.getErrorLoadNumber());
    }

}
