package perezom;


import org.junit.Assert;
import org.junit.Test;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class WekaIrisClassifierTest {

    @Test
    public void canReadDataTest() {
        var data = WekaIrisClassifier.readData();
        assertNotNull(data);
        assertTrue(data instanceof Instances);
    }


    @Test
    public void getFirstRowOfDataTest() {
        var data = WekaIrisClassifier.readData();
        var summaryString = data.toSummaryString();
        assertNotNull(summaryString);

        var firstInstance = data.get(0);
        assertEquals(5.1, firstInstance.value(0), 0);
    }

    @Test
    public void getPlot() {
        var instances = new Instances("test", new ArrayList<>(), 1);
        assertNotNull(WekaIrisClassifier.getPlot(instances));
    }

}