package test;

import static org.junit.Assert.*;
import org.junit.*;

import simulation.*;

/**
 * This is a JUnit test class. You will be testing your code here.
 * 
 * You must use the provided Farm Simulation classes and methods to design
 * your test cases for this assignment. Consult the assignment
 * description for more details.
 * 
 * @author Rachel Saini
 * @author Hemadharshinii Sendhilvel
 */

public class FarmTest {
    
    /**
     * This is the empty farm template.
     * 
     * DO NOT EDIT THE TEMPLATE.
     */
    public static char[][] farmTemplate = {
        {'E', 'E', 'E', 'E', 'E'},
        {'E', 'E', 'E', 'E', 'E'},
        {'E', 'E', 'E', 'E', 'E'},
        {'E', 'E', 'E', 'E', 'E'},
        {'E', 'E', 'E', 'E', 'E'},
    };
    

    /**
     * This test checks that each tile of the farm
     * is empty and does not contain a crop.
     */
    @Test
    public void testNoCrops() {
        // WRITE YOUR CODE HERE
        fail("Test not implemented"); // Remove this line once you write the test
        
    }


    /**
     * This test ensures whether each row on the farm has the appropriate crop
     * planted on it, with wheat being planted on the even-indexed rows, and
     * parsnips planted on the odd-indexed rows.
     * 
     * Then you must check that there is more wheat planted than parsnips.
     */
    @Test
    public void testEvenAndOdd(){
        // WRITE YOUR CODE HERE
        fail("Test not implemented"); // Remove this line once you write the test

    }


    /**
     * This test checks to ensure we get the expected amount of profit
     * from harvesting crops.
     * 
     * You are expected to go through the entire farm simulation, calling each
     * method in Farm.java in order (plantCrops(), waterFarm(), growCrops(), harvestCrops()).
     * 
     * You are not limited to planting crops in an even-odd arrangement
     * as in the previous test case.
     */
    @Test
    public void testGetProfit(){
        // WRITE YOUR CODE HERE
        fail("Test not implemented"); // Remove this line once you write the test
        
    }

}
