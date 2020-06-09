package perezom;

import perezom.plot.ScatterPlot;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.unsupervised.instance.RemovePercentage;

import java.io.File;
import java.util.Random;

public class WekaIrisClassifier {

    public static void main(String[] args) throws Exception {
        //Load data
        var data = readData();

        //Explore data
        System.out.println(data.toSummaryString());

        //Get Train/Test split
        data.randomize(new Random(42));
        int trainSize = (int) Math.round(data.numInstances() * 0.8);
        int testSize = data.numInstances() - trainSize;
        Instances train = new Instances(data, 0, trainSize);
        Instances test = new Instances(data, trainSize, testSize);

        //Model data
        var classifier = new RandomForest();
        classifier.buildClassifier(train);

        //Validate data:
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(classifier, test);
        System.out.println(
                eval.toSummaryString("Results Train/Test Split:\n",true)
        );

        //Validate data: K-fold cross validation
        classifier.buildClassifier(data); //we need to rebuild the classifier with the whole dataset.
        eval = new Evaluation(data);
        eval.crossValidateModel(classifier, data, 10, new Random(42));
        System.out.println(eval.toSummaryString("Results K-Fold Cross Validation:\n",true));
    }

    public static Instances readData() {
        Instances data = null;

        try {

            var dataSource = new ConverterUtils.DataSource("iris.csv");
            data = dataSource.getDataSet();
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static ScatterPlot getPlot(Instances data) {
        var plot = new ScatterPlot(data);
        return plot;
    }


}
