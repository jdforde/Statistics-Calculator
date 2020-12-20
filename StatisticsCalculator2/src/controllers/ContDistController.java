package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;

import auxstats.DistFuncs;

public class ContDistController extends DistFuncs{

	boolean uniformPressed = true;
	boolean normalPressed;
	boolean expPressed;
	DecimalFormat df = new DecimalFormat("0.####");

    @FXML
    private RadioButton normal;
    @FXML
    private Text label1;
    @FXML
    private Text label2;
    @FXML
    private ToggleGroup group1;
    @FXML
    private Label errorMsg;
    @FXML
    private Text label3;
    @FXML
    private Text label4;
    @FXML
    private RadioButton uniform;
    @FXML
    private TextField text3;
    @FXML
    private TextField text4;
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private AnchorPane contDist;
    @FXML
    private Button calculate;
    @FXML
    private RadioButton exp;
    @FXML
    private RadioButton cont;
    @FXML
    private Text result3;
    @FXML
    private Text result2;
    @FXML
    private Text result1;
    
    @FXML
    void uniformPressed(ActionEvent event) {
    	errorMsg.setVisible(false);
    	
    	uniformPressed = true;
    	normalPressed = false;
    	expPressed = false;
    	
    	label1.setText("Lower Bound:");
    	text1.setText("");
    	label2.setText("Upper Bound:");
    	text2.setText("");
    	text2.setPromptText("");
    	label3.setVisible(true);
    	text3.setVisible(true);
    	label3.setText("P(X ≤ x):");
    	text3.setText("");
    	text3.setPromptText("Optional");
    	label4.setVisible(false);
    	text4.setVisible(false);
    	
    	result1.setText("Mean:");
    	result2.setVisible(true);
    	result3.setVisible(true);
    	result2.setText("Variance:");
    	result3.setText("CDF:");
    }

    @FXML
    void normalPressed(ActionEvent event) {
    	errorMsg.setVisible(false);
    	
    	uniformPressed = false;
    	normalPressed = true;
    	expPressed = false;
    	
    	label1.setText("Lower Bound:");
    	text1.setText("");
    	label2.setText("Upper Bound:");
    	text2.setText("");
    	text2.setPromptText("");
    	label3.setVisible(true);
    	text3.setVisible(true);
    	label3.setText("Mean:");
    	text3.setText("");
    	text3.setPromptText("");
    	label4.setVisible(true);
    	label4.setText("SD:");
    	text4.setVisible(true);
    	text4.setText("");
    	
    	result1.setText("CDF:");
    	result2.setVisible(false);
    	result3.setVisible(false);
    }

    @FXML
    void expPressed(ActionEvent event) {
    	errorMsg.setVisible(false);
    	
    	uniformPressed = false;
    	normalPressed = false;
    	expPressed = true;
    	
    	label1.setText("γ:");
    	text1.setText("");
    	label2.setText("P(X ≤ x):");
    	text2.setPromptText("Optional");
    	text2.setText("");
    	label3.setVisible(false);
    	text3.setVisible(false);
    	label4.setVisible(false);
    	text4.setVisible(false);
    
    	result1.setText("Mean:");
    	result2.setVisible(true);
    	result2.setText("Variance:");
    	result3.setVisible(true);
    	result3.setText("CDF:");
    }

    @FXML
    void CalcClick(ActionEvent event) {
    	errorMsg.setVisible(false);
    	if (uniformPressed) 
    		UniformCalculations();
    	
    	if (normalPressed) 
    		NormalCalculations();
    	
    	if (expPressed) 
    		ExpCalculations();
    }
    



    @FXML
    void ResetClick(ActionEvent event) {
    	if (uniformPressed) 
    		uniformPressed(event);
    	
    	if (normalPressed) 
    		normalPressed(event);
    	
    	if (expPressed) 
    		expPressed(event);
    	
    }
    
    void UniformCalculations() {

		try {
			double lowerBound = Double.parseDouble(text1.getText());
			double upperBound = Double.parseDouble(text2.getText());
			if (lowerBound > upperBound) {
    			errorMsg.setVisible(true);
    			errorMsg.setText("Incorrect bounds");
    			result1.setText("Mean:");
    			result2.setText("Variance:");
    			result3.setText("CDF:");
			} else {
    			if (!text3.getText().equals("")) {
    				double CDFval = Double.parseDouble(text3.getText());
    				result3.setText("CDF: " + df.format(ContUniformCDF(lowerBound, upperBound, CDFval))); 
    			}
    			
    			result1.setText("Mean: " + df.format(UniformMean(lowerBound, upperBound)));
    			result2.setText("Variance: " + df.format(ContUniformVar(lowerBound, upperBound)));
			}
			
		} catch (NumberFormatException e) {
			errorMsg.setVisible(true);
			errorMsg.setText("Incorrect number format");
			result1.setText("Mean:");
			result2.setText("Variance:");
			result3.setText("CDF:");
		}
    }
    
    void NormalCalculations() {
    	try {
			double lowerBound = Double.parseDouble(text1.getText());
			double upperBound = Double.parseDouble(text2.getText());
			if (lowerBound > upperBound) {
				errorMsg.setVisible(true);
    			errorMsg.setText("Check the bounds");
    			result1.setText("CDF:");
			} else {
    			double mean = Double.parseDouble(text3.getText());
    			double SD = Double.parseDouble(text4.getText());
    			result1.setText("CDF: " + df.format(NormalCDF(lowerBound, upperBound, mean, SD)));
			}

			
		} catch (NumberFormatException e) {
			errorMsg.setVisible(true);
			errorMsg.setText("Incorrect number format");
			result1.setText("CDF:");
		} catch (NotStrictlyPositiveException e) {
			errorMsg.setVisible(true);
			errorMsg.setText("SD must be greater than 0");
			result1.setText("CDF:");
		}
    }

    void ExpCalculations() {
    	try {
			double gamma = Double.parseDouble(text1.getText());
			if (!text2.getText().equals("")) {
				double x = Double.parseDouble(text2.getText());
				result3.setText("CDF: " + df.format(ExpCDF(gamma, x)));
			}
			
			result1.setText("Mean: " + df.format(1/gamma));
			result2.setText("Variance:" + df.format(1/Math.pow(gamma, 2)));
			
		} catch (NumberFormatException e) {
			errorMsg.setVisible(true);
			errorMsg.setText("Incorrect number format");
			result1.setText("Mean:");
			result2.setText("Variance:");
			result3.setText("CDF:");
		} catch (NotStrictlyPositiveException e) {
			errorMsg.setVisible(true);
			errorMsg.setText("Invalid gamma value");
			result1.setText("Mean:");
			result2.setText("Variance:");
			result3.setText("CDF:");
		}
    }
}