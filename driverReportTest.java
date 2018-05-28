import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertEquals;


public class driverReportTest {


    @Test   // testing if get minutes gets the correct amount of minutes from start to end.
    public void getMinutesTest() {
        driverReport testOne = new driverReport();
        assertEquals(testOne.getMinutes("09:00", "09:59"), 59);
        assertEquals(testOne.getMinutes("10:00", "10:30"), 30);
        assertEquals(testOne.getMinutes("12:05", "13:25"), 80);
        assertEquals(testOne.getMinutes("07:00", "12:00"), 300);
    }

    @Test
    public void driverMilesTest() {
        driverReport testTwo = new driverReport();
        String filePath = "driverData.txt";

        // read the file for the test
        testTwo.readDriverFile(filePath);

        // map keys
        Set testMap = testTwo.getDriverMiles().keySet();

        // key values
        Collection valuesTest = testTwo.getDriverMiles().values();

        assertEquals(testMap.toString(), "[Dan, Alex, Bob]");
        assertEquals(valuesTest.toString(), "[78.2, 84.0, 53.6]");

    }

    @Test
    public void driverMilesTestTwo() {
        driverReport testTwo = new driverReport();
        String filePath = "driverTwoData.txt";

        // read the file for the test
        testTwo.readDriverFile(filePath);

        // map keys
        Set testMap = testTwo.getDriverMiles().keySet();

        // key values
        Collection valuesTest = testTwo.getDriverMiles().values();

        assertEquals(testMap.toString(), "[Dan, Alex, Bob]");
        assertEquals(valuesTest.toString(), "[40.7, 76.0, 0.0]");

    }

    @Test
    public void driverMPHTest() {
        driverReport testThree = new driverReport();
        String filePath = "driverData.txt";

        // read the file for the test
        testThree.readDriverFile(filePath);
        //outputs data to calculate MPH
        testThree.getDriverData();

        // map keys
        Set testMap = testThree.getDriverMPH().keySet();

        // key values
        Collection valuesTest = testThree.getDriverMPH().values();

        assertEquals(testMap.toString(), "[Dan, Alex, Bob]");
        assertEquals(valuesTest.toString(), "[47, 34, 38]");

    }

    @Test
    public void driverMPHTestTwo() {
        driverReport testThree = new driverReport();
        String filePath = "driverTwoData.txt";

        // read the file for the test
        testThree.readDriverFile(filePath);
        //outputs data to calculate MPH
        testThree.getDriverData();

        // map keys
        Set testMap = testThree.getDriverMPH().keySet();

        // key values
        Collection valuesTest = testThree.getDriverMPH().values();

        assertEquals(testMap.toString(), "[Dan, Alex]");
        assertEquals(valuesTest.toString(), "[9, 11]");

    }
}

