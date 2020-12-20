package controllers;

import java.text.DecimalFormat;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;

import auxstats.SingleSampFuncs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SingleSampHypController extends SingleSampFuncs{

	ObservableList<String> meanOptions = FXCollections.observableArrayList("μ ≠ μ₀", "μ < μ₀", "μ > μ₀");
	ObservableList<String> varOptions = FXCollections.observableArrayList("σ² ≠ σ₀²", "σ² < σ₀²", "σ² > σ₀²");
	ObservableList<String> propOptions = FXCollections.observableArrayList("p ≠ p₀", "p < p₀", "p > p₀");
	DecimalFormat df = new DecimalFormat("0.####");
	boolean zTestPressed = true;
	boolean tTestPressed;
	boolean chiTestPressed;
	boolean propTestPressed;
	
    @FXML
    private ChoiceBox<String> sideChoice;

    @FXML
    private Text ans;

    @FXML
    private AnchorPane singleSampInt;

    @FXML
    private Text label3;

    @FXML
    private RadioButton zInt;

    @FXML
    private Text label1;

    @FXML
    private RadioButton tInt;

    @FXML
    private Text label12;

    @FXML
    private Text label2;

    @FXML
    private Text label11;

    @FXML
    private RadioButton chiInt;

    @FXML
    private TextField sizeText;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField text3;

    @FXML
    private Button reset;

    @FXML
    private Button calc;

    @FXML
    private RadioButton propInt;

    @FXML
    private ToggleGroup group;

    @FXML
    private void initialize() {
    	sideChoice.setItems(meanOptions);
    	sideChoice.setValue("μ ≠ μ₀");
    	//needs to be Verdana like everything else
    }
    
    @FXML
    void zTestPressed(ActionEvent event) {
    	sideChoice.setItems(meanOptions);
    	sideChoice.setValue("μ ≠ μ₀");
    	ans.setVisible(false);
    	zTestPressed = true;
    	tTestPressed = false;
    	chiTestPressed = false;
    	propTestPressed = false;
    	
    	label1.setText("Population SD:");
    	text1.setText("");
    	text2.setText("");
    	label2.setVisible(true);
    	text2.setVisible(true);
    	label2.setText("Sample Mean:");
    	sizeText.setText("");
    	text3.setText("");
    	label3.setText("μ₀:");
    }

    @FXML
    void tTestPressed(ActionEvent event) {
    	sideChoice.setItems(meanOptions);
    	sideChoice.setValue("μ ≠ μ₀");
    	ans.setVisible(false);
    	zTestPressed = false;
    	tTestPressed = true;
    	chiTestPressed = false;
    	propTestPressed = false;
    	
    	label1.setText("Sample  SD:");
    	text1.setText("");
    	text2.setText("");
    	label2.setVisible(true);
    	text2.setVisible(true);
    	label2.setText("Sample Mean:");
    	sizeText.setText("");
    	text3.setText("");
    	label3.setText("μ₀:");
    }

    @FXML
    void chiTestPressed(ActionEvent event) {
    	sideChoice.setItems(varOptions);
    	sideChoice.setValue("σ² ≠ σ₀²");
    	ans.setVisible(false);
    	zTestPressed = false;
    	tTestPressed = false;
    	chiTestPressed = true;
    	propTestPressed = false;
    	
    	label1.setText("Sample  SD:");
    	text1.setText("");
    	label2.setVisible(false);
    	text2.setVisible(false);
    	sizeText.setText("");
    	text3.setText("");
    	label3.setText("σ₀²:");
    }

    @FXML
    void propTestPressed(ActionEvent event) {
    	sideChoice.setItems(propOptions);
    	sideChoice.setValue("p ≠ p₀");
    	ans.setVisible(false);
    	zTestPressed = false;
    	tTestPressed = false;
    	chiTestPressed = false;
    	propTestPressed = true;
    	
    	label1.setText("x:");
    	text1.setText("");
    	label2.setVisible(false);
    	text2.setVisible(false);
    	sizeText.setText("");
    	text3.setText("");
    	label3.setText("p₀:");
    }


    @FXML
    void ResetClick(ActionEvent event) {
    	if (zTestPressed) 
    		zTestPressed(event);
    	if (tTestPressed)
    		tTestPressed(event);
    	if (chiTestPressed)
    		chiTestPressed(event);
    	if (propTestPressed)
    		propTestPressed(event);
    }

    @FXML
    void CalcClicked(ActionEvent event) {
    	if (zTestPressed) {
    		zTestCalc();
    	}
    	if (tTestPressed) {
    		tTestCalc();
    	}
    	
    	if (chiTestPressed) {
    		chiTestCalc();
    	}
    	
    	if (propTestPressed) {
    		propTestCalc();
    	}
    }
    
    void zTestCalc() {
    	try {
    		double popSD = Double.parseDouble(text1.getText());
    		double sampMean = Double.parseDouble(text2.getText());
    		int n = Integer.parseInt(sizeText.getText());
    		double muNaught = Double.parseDouble(text3.getText());
    		ans.setFill(Color.BLACK);
    		if (popSD < 0) {
    			throw new NotStrictlyPositiveException(popSD);
    		}
    		if (n < 0) {
    			throw new NumberFormatException();
    		}
    		if (sideChoice.getValue().equals("μ ≠ μ₀")) {
    			double pval = ZTest(popSD, sampMean, muNaught, n, 0);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else if (sideChoice.getValue().equals("μ < μ₀")) {
    			double pval = ZTest(popSD, sampMean, muNaught, n, 1);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else {
    			double pval = ZTest(popSD, sampMean, muNaught, n, 2);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		}

    	} catch (NumberFormatException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("Incorrect number format");
    	} catch (NotStrictlyPositiveException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("SD must be greater than 0");
    	} catch (OutOfRangeException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("Incorrect probability value");
    	}
    }
    
    void tTestCalc() {
      	try {
    		double sampSD = Double.parseDouble(text1.getText());
    		double sampMean = Double.parseDouble(text2.getText());
    		int n = Integer.parseInt(sizeText.getText());
    		double muNaught = Double.parseDouble(text3.getText());
    		ans.setFill(Color.BLACK);
    		if (sampSD < 0) {
    			throw new NumberFormatException();
    		}
    		if (sideChoice.getValue().equals("μ ≠ μ₀")) {
    			double pval = TTest(sampSD, sampMean, muNaught, n, 0);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else if (sideChoice.getValue().equals("μ < μ₀")) {
    			double pval = TTest(sampSD, sampMean, muNaught, n, 1);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else {
    			double pval = TTest(sampSD, sampMean, muNaught, n, 2);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		}

    	} catch (NumberFormatException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("Incorrect number format");
    	} catch (NotStrictlyPositiveException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("Invalid degrees of freedom");
    	} catch (OutOfRangeException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("Incorrect probability value");
    	}
    }
    
    //double sampSD, double sigmaNaught, int n, int test)
    void chiTestCalc() {
       	try {
    		double sampSD = Double.parseDouble(text1.getText());
    		int n = Integer.parseInt(sizeText.getText());
    		double sigmaNaught = Double.parseDouble(text3.getText());
    		ans.setFill(Color.BLACK);
    		if (sampSD < 0) {
    			throw new NumberFormatException();
    		}
    		if (sideChoice.getValue().equals("σ² ≠ σ₀²")) {
    			double pval = ChiTest(sampSD, sigmaNaught, n, 0);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else if (sideChoice.getValue().equals("σ² < σ₀²")) {
    			double pval = ChiTest(sampSD, sigmaNaught, n, 1);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else {
    			double pval = ChiTest(sampSD, sigmaNaught, n, 2);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		}

    	} catch (NumberFormatException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("Incorrect number format");
    	} catch (NotStrictlyPositiveException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("Invalid degrees of freedom");
    	} catch (OutOfRangeException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("Incorrect probability value");
    	}
    }
    
    void propTestCalc() {
    	try {
    		double x = Double.parseDouble(text1.getText());
    		int n = Integer.parseInt(sizeText.getText());
    		double pNaught = Double.parseDouble(text3.getText());
    		ans.setFill(Color.BLACK);
    		
    		if (n == 0) {
    			throw new ArithmeticException();
    		}
    		if (x < 0 || n < 0) {
    			throw new OutOfRangeException(x, 1, 100000);
    		}
    		if (x > n) {
    			throw new NumberFormatException();
    		}
    		if (sideChoice.getValue().equals("p ≠ p₀")) {
    			double pval = PropTest(x, pNaught, n, 0);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else if (sideChoice.getValue().equals("p < p₀")) {
    			double pval = PropTest(x, pNaught, n, 1);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else {
    			double pval = PropTest(x, pNaught, n, 2);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		}

    	} catch (NumberFormatException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("Incorrect number format");
    	} catch (ArithmeticException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("Can't divide by 0");
    	} catch (OutOfRangeException e) {
    		ans.setVisible(true);
    		ans.setFill(Color.RED);
    		ans.setText("Can't have negative values");
    	}
    }

}
