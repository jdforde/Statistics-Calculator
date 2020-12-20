package controllers;

import java.text.DecimalFormat;

import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import auxstats.DistFuncs;

public class DiscreteDistController extends DistFuncs{

	ObservableList<String> options = FXCollections.observableArrayList(" ", "X = x", "X ≤ x");
	DecimalFormat df = new DecimalFormat("0.####");
	boolean isUniform = true;
    boolean isBinom;
    boolean isPoiss;
    
    @FXML
    private Text label1;
    @FXML
    private Text label2;
    @FXML
    private Label errorMsg;
    @FXML
    private Text label3;
    @FXML
    private RadioButton uniform;
    @FXML
    private RadioButton binom;
    @FXML
    private RadioButton poiss;
    @FXML
    private TextField text3;
    @FXML
    private TextField text4;
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private Button calculate;
    @FXML
    private AnchorPane discreteDist;
    @FXML
    private ChoiceBox<String> pdfcdf;
    @FXML
    private Text result3;
    @FXML
    private Text result2;
    @FXML
    private Text result1;
    
  

    @FXML
    private void initialize() {
    	pdfcdf.setItems(options);
    	pdfcdf.setValue(" ");
    	pdfcdf.setVisible(false);
    }
    	
    @FXML
    void CalcClick(ActionEvent event) {
    	errorMsg.setVisible(false);
    	
    	if (isUniform) 
    		UniformCalculations();
    	
    	if (isBinom) 
    		BinomCalculations();
    	
    	if (isPoiss) 
    		PoissCalculations();
  
    }
  
    
    @FXML
    void uniformPressed(ActionEvent event) {
    	errorMsg.setVisible(false);
    	label1.setText("Lower Bound:");
    	text1.setText("");
    	label2.setText("Upper Bound:");
    	text2.setText("");
    	label3.setVisible(false);
    	text3.setVisible(false);
    	pdfcdf.setVisible(false);
    	text4.setVisible(false);
    	
    	result1.setText("Mean:");
    	result2.setText("Variance:");
    	result3.setText("SD:");
    	
    	isUniform = true;
    	isBinom = false;
    	isPoiss = false;
    }

    @FXML
    void binomPressed(ActionEvent event) {
    	errorMsg.setVisible(false);
    	label1.setText("Number of trials:");
    	text1.setText("");
    	label2.setText("Probability of success:");
    	text2.setText("");
    	label3.setVisible(false);
    	text3.setVisible(false);
    	pdfcdf.setVisible(true);
    	pdfcdf.setValue(" ");
    	text4.setVisible(true);
    	text4.setText("");
    	
    	result1.setText("Mean:");
    	result2.setText("Variance:");
    	result3.setText("CDF/PDF:");
    	
    	isUniform = false;
    	isBinom = true;
    	isPoiss = false;
    	
    	
    }

    @FXML
    void poissPressed(ActionEvent event) {
    	errorMsg.setVisible(false);
    	label1.setText("γ:");
    	text1.setText("");
    	label2.setText("T:");
    	text2.setText("");
    	label3.setVisible(false);
    	text3.setVisible(false);
    	pdfcdf.setVisible(true);
    	text4.setVisible(true);
    	text4.setText("");
    	
    	result1.setText("Mean:");
    	result2.setText("Variance:");
    	result3.setText("CDF/PDF:");
    	
    	isUniform = false;
    	isBinom = false;
    	isPoiss = true;
      }
    
    @FXML
    void ResetClick(ActionEvent event) {
    	if (isUniform)
    		uniformPressed(event);
    	if (isBinom)
    		binomPressed(event);
    	if (isPoiss)
    		poissPressed(event);
    }
    
    void UniformCalculations() {
		try {
    		double lower = Double.parseDouble(text1.getText());
    		double upper = Double.parseDouble(text2.getText());
    		if (lower > upper) {
    			errorMsg.setVisible(true);
    			errorMsg.setText("Incorrect bounds");
    			result1.setText("Mean:");
    			result2.setText("Variance:");
    			result3.setText("SD:");
    		} else {
	    		result1.setText("Mean: " + df.format(UniformMean(lower, upper)));
	    		result2.setText("Variance: " + df.format(UniformVar(lower, upper)));
	    		result3.setText("SD: " + df.format(Math.sqrt(UniformVar(lower, upper))));
	    	}
		} catch (NumberFormatException e) {
			errorMsg.setVisible(true);
			errorMsg.setText("Incorrect number format");
			result1.setText("Mean:");
			result2.setText("Variance:");
			result3.setText("SD:");
		}
    }

    void BinomCalculations() {
		try {
		int trials = Integer.parseInt(text1.getText());
		double probability = Double.parseDouble(text2.getText());
			result1.setText("Mean: " + df.format(BinomMean(trials, probability)));
			result2.setText("Variance: " + df.format(BinomVar(trials, probability)));
			if (pdfcdf.getValue().equals("X = x")) {
				int successes = Integer.parseInt(text4.getText());
				result3.setText("CDF/PDF: " + df.format(BinomPDF(trials, probability, successes)));
			}
			if (pdfcdf.getValue().equals("X ≤ x")) {
				int successes = Integer.parseInt(text4.getText());
				result3.setText("CDF/PDF: " + df.format(BinomCDF(trials, probability, successes)));
    			}
 
    	} catch (NumberFormatException e) {
    		errorMsg.setVisible(true);
			errorMsg.setText("Incorrect number format");
			result1.setText("Mean:");
			result2.setText("Variance:");
			result3.setText("CDF/PDF:");
    	} catch (NotPositiveException e) {
    		errorMsg.setVisible(true);
			errorMsg.setText("Can't have negative trials");
			result1.setText("Mean:");
			result2.setText("Variance:");
			result3.setText("CDF/PDF:");
    	} catch (OutOfRangeException e) {
    		errorMsg.setVisible(true);
			errorMsg.setText("Incorrect probability value");
			result1.setText("Mean:");
			result2.setText("Variance:");
			result3.setText("CDF/PDF:");
    	}
    }

    void PoissCalculations() {
   		try {
    		double rate = Double.parseDouble(text1.getText());
    		double time = Double.parseDouble(text2.getText());
			result1.setText("Mean: " + df.format(PoissMean(rate, time)));
			result2.setText("Variance: " + df.format(PoissMean(rate, time)));
			if (pdfcdf.getValue().equals("X = x")) {
				int successes = Integer.parseInt(text4.getText());
				result3.setText("CDF/PDF: " + df.format(PoissPDF(rate, time, successes))); 
			}
			if (pdfcdf.getValue().equals("X ≤ x")) {
				int successes = Integer.parseInt(text4.getText());
				result3.setText("CDF/PDF: " + df.format(PoissCDF(rate, time, successes)));
			}
    	} catch (NumberFormatException e) {
    		errorMsg.setVisible(true);
			errorMsg.setText("Incorrect number format");
			result1.setText("Mean:");
			result2.setText("Variance:");
			result3.setText("CDF/PDF:");
    	} catch (NotStrictlyPositiveException e) {
    		errorMsg.setVisible(true);
			errorMsg.setText("Mean cannot be negative");
			result1.setText("Mean:");
			result2.setText("Variance:");
			result3.setText("CDF/PDF:");
    	}
    }
}