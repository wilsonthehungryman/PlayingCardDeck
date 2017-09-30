/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Wilson
 */
public class FaceNGTest {
    
    public FaceNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of values method, of class Face.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Face[] expResult = { Face.ACE, Face.TWO, Face.THREE, Face.FOUR, Face.FIVE, Face.SIX, Face.SEVEN, Face.EIGHT, Face.NINE, Face.TEN, Face.JACK, Face.QUEEN, Face.KING, Face.JOKER };
        Face[] result = Face.values();
        assertEquals(result, expResult);
    }

    /**
     * Test of valueOf method, of class Face.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "QUEEN";
        Face expResult = Face.QUEEN;
        Face result = Face.valueOf(name);
        assertEquals(result, expResult);
    }

    /**
     * Test of getValue method, of class Face.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Face instance = Face.JACK;
        int expResult = 11;
        int result = instance.getValue();
        assertEquals(result, expResult);
    }

    /**
     * Test of toString method, of class Face.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Face instance = Face.KING;
        String expResult = "King";
        String result = instance.toString();
        assertEquals(result, expResult);
    }
    
}
