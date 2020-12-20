/**
 * 
 * @author: Jakob Forde
 * 
 * This class represents the controller for the "Basic Stats" page. It takes data
 * and computes the mean, size, variance, sample variance, standard deviation, and
 * sample standard deviation. The user can select which of the statistical values they
 * need. This page also allows the user to create a histogram out of their given data. Data
 * must be separated by commas, tabs, OR newlines. 
 */
package controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.text.DecimalFormat;
import java.util.ArrayList;
import auxstats.CommonCalc;
public class BasicStatsController extends CommonCalc{


	private ArrayList<Double> values;
	private DecimalFormat df = new DecimalFormat("0.####");
	private String[] valuesString;
	
    @FXML
    private Label sampVar;
    @FXML
    private Label SD;
    @FXML
    private Label size;
    @FXML
    private TextArea stats;
    @FXML
    private Label sampSD;
    @FXML
    private Label mean;
    @FXML
    private Label var;
    @FXML
    private Label errorMsg;

    
    void ResetLabels() {
    	mean.setText("Mean: ");
    	size.setText("Size: ");
    	var.setText("Variance: ");
    	sampVar.setText("Sample Variance: ");
    	SD.setText("SD: ");
    	sampSD.setText("Sample SD: ");
    }
    
    void Splitter(String statsText) {
    	if (statsText.indexOf(',', 0) != -1) {
    		valuesString = statsText.split(",");
		} else if (statsText.indexOf('\n', 0) != -1) {
			valuesString = statsText.split("\n");
		} else if (statsText.indexOf(" ", 0) != -1){
			valuesString = statsText.split(" ");
		} else {
			valuesString = statsText.split("\t");
		}
    	
    }
    
    @FXML
    void CalcClick(ActionEvent event) {
    	String statsText = stats.getText();
    	
    	values = new ArrayList<Double>();
    	if (statsText.equals("")) {
    		errorMsg.setVisible(true);
    		errorMsg.setText("Please type in values.");
    		ResetLabels();	
    	} else {
    		errorMsg.setVisible(false);
    		Splitter(statsText);
    		
    		
    		try {
	    		for (int i = 0; i< valuesString.length; i++) 
	    			values.add(Double.parseDouble(valuesString[i]));
	    		
	    		mean.setText("Mean: " + df.format(getMean(values)));
	    		size.setText("Size: " + values.size());
	    		var.setText("Variance: " + df.format(getVar(values)));
	    		sampVar.setText("Sample Variance: " + df.format(getSampVar(values)));
	    		SD.setText("SD: " + df.format(getSD(values)));
	    		sampSD.setText("Sample SD: " + df.format(getSampSD(values)));
	    		
	    		if (values.size() == 1) {
	    			sampVar.setText("Sample Variance: --");
	    			sampSD.setText("Sample SD: --");
	    		}
	    	
    		} catch(NumberFormatException e) {
    		 		errorMsg.setVisible(true);
    		 		errorMsg.setText("Incorrect number format.");
    		 		ResetLabels();
    		}
    	
    	}
    }
    	
    @FXML
    void ResetClick(ActionEvent event) {
    	ResetLabels();
    	stats.setText("");
    	errorMsg.setVisible(false);
    }

    //will add the code later....
    @FXML
    void HistClick(ActionEvent event) {

    }


	}
