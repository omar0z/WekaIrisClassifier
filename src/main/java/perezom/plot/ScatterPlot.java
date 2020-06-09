package perezom.plot;

import java.awt.BorderLayout;
import java.util.Random;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.examples.ExamplePanel;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.Insets2D;
import weka.core.Instance;
import weka.core.Instances;

import javax.swing.*;

public class ScatterPlot extends ExamplePanel {
    /**
     * Version id for serialization.
     */
    private static final long serialVersionUID = -412699430625953887L;

    @SuppressWarnings("unchecked")
    public ScatterPlot(Instances instances) {

        DataTable data = new DataTable(Double.class, Double.class);

        for(var instance : instances){
            data.add(instance.value(0), instance.value(2));
        }

        // Create a new xy-plot
        XYPlot plot = new XYPlot(data);

        // Format plot
        plot.setInsets(new Insets2D.Double(20.0, 40.0, 40.0, 40.0));
        plot.getTitle().setText(getDescription());

        // Format points
        plot.getPointRenderer(data).setColor(COLOR1);

        // Add plot to Swing component
        add(new InteractivePanel(plot), BorderLayout.CENTER);
    }

    @Override
    public String getTitle() {
        return "Scatter plot";
    }

    @Override
    public String getDescription() {
        return String.format("Scatter plot with Iris data points");
    }

    public JFrame showInFrame() {
        JFrame frame = new JFrame(this.getTitle());
        frame.getContentPane().add(this, "Center");
        frame.setDefaultCloseOperation(3);
        frame.setSize(this.getPreferredSize());
        frame.setVisible(true);
        return frame;
    }
}