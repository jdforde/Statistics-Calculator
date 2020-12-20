package controllers;

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
import java.text.DecimalFormat;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;

public class SingleSampIntController extends SingleSampFuncs{

	ObservableList<String> options = FXCollections.observableArrayList("Two-Sided", "Lower", "Upper");
	DecimalFormat df = new DecimalFormat("0.####");
	boolean zIntPressed = true;
	boolean tIntPressed;
	boolean chiIntPressed;
	boolean propIntPressed;
	
    @FXML
    private Text nullHyp;

    @FXML
    private ToggleGroup group11;

    @FXML
    private ChoiceBox<String> sideChoice;

    @FXML
    private Text ans;

    @FXML
    private AnchorPane singleSampInt;
  
    @FXML
    private RadioButton zInt;

    @FXML
    private Text label1;
    
    @FXML
    private Text label2;

    @FXML
    private RadioButton tInt;

    @FXML
    private ToggleGroup group1;

    @FXML
    private RadioButton chiInt;

    @FXML
    private TextField sizeText;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField confidenceText;

    @FXML
    private Button reset;

    @FXML
    private Button calc;

    @FXML
    private ToggleGroup group13;

    @FXML
    private ToggleGroup group12;

    @FXML
    private RadioButton propInt;

    @FXML
    private void initialize() {
    	sideChoice.setItems(options);
    	sideChoice.setValue("Two-Sided");
    	//needs to be Verdana like everything else
    }
    @FXML
    void zIntPressed(ActionEvent event) {
    	ans.setVisible(false);
    	zIntPressed = true;
    	tIntPressed = false;
    	chiIntPressed = false;
    	propIntPressed = false;
    	
    	label1.setText("Population SD:");
    	text1.setText("");
    	text2.setText("");
    	label2.setVisible(true);
    	text2.setVisible(true);
    	label2.setText("Sample Mean:");
    	sizeText.setText("");
    	confidenceText.setText("");
    }

    @FXML
    void tIntPressed(ActionEvent event) {
    	ans.setVisible(false);
    	zIntPressed = false;
    	tIntPressed = true;
    	chiIntPressed = false;
    	propIntPressed = false;
    	
    	label1.setText("Sample SD:");
    	label2.setVisible(true);
    	label2.setText("Sample Mean:");
    	text2.setVisible(true);
    	text1.setText("");
    	text2.setText("");
    	sizeText.setText("");
    	confidenceText.setText("");
    	
    }

    @FXML
    void chiIntPressed(ActionEvent event) {
    	ans.setVisible(false);
    	zIntPressed = false;
    	tIntPressed = false;
    	chiIntPressed = true;
    	propIntPressed = false;
    	
    	label1.setText("Sample Variance:");
    	text2.setVisible(false);
    	label2.setVisible(false);
    	text1.setText("");
    	sizeText.setText("");
    	confidenceText.setText("");
    }

    @FXML
    void propIntPressed(ActionEvent event) {
    	ans.setVisible(false);
    	zIntPressed = false;
    	tIntPressed = false;
    	chiIntPressed = false;
    	propIntPressed = true;
    	
    	label1.setText("x:");
    	text2.setVisible(false);
    	label2.setVisible(false);
    	text1.setText("");
    	sizeText.setText("");
    	confidenceText.setText("");
    }


    @FXML
    void ResetClick(ActionEvent event) {
    	sideChoice.setValue("Two-Sided");
    	
    	if (zIntPressed) 
    		zIntPressed(event);
    	if (tIntPressed)
    		tIntPressed(event);
    	if (chiIntPressed)
    		chiIntPressed(event);
    	if (propIntPressed)
    		propIntPressed(event);
    		
    }

    @FXML
    void CalcClicked(ActionEvent event) {
    	if (zIntPressed) {
    		zIntCalc();
    	}
    	if (tIntPressed) {
    		tIntCalc();
    	}
    	
    	if (chiIntPressed) {
    		chiIntCalc();
    	}
    	
    	if (propIntPressed) {
    		propIntCalc();
    	}
    }
    
    void zIntCalc() { 
    	try {
    		double popSD = Double.parseDouble(text1.getText());
    		double sampMean = Double.parseDouble(text2.getText());
    		int n = Integer.parseInt(sizeText.getText());
    		double confidence = Double.parseDouble(confidenceText.getText());
    		if (popSD < 0) {
    			throw new NotStrictlyPositiveException(popSD);
    		}
    		if (n < 0) {
    			throw new NumberFormatException();
    		}
    		double[] interval = new double[2];
    		ans.setFill(Color.BLACK);
    		if (sideChoice.getValue().equals("Two-Sided")) {
    			interval = ZInterval(true, true, popSD, sampMean, n, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[1]) + " <  μ  < " + df.format(interval[0]));
    		} else if (sideChoice.getValue().equals("Lower")) {
    			interval = ZInterval(false, true, popSD, sampMean, n, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[0]) + " < μ");
    		} else {
    			interval = ZInterval(true, false, popSD, sampMean, n, confidence);
    			ans.setVisible(true);
    			ans.setText("μ > " + df.format(interval[0]));
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
    
    void tIntCalc() {
    	try {
    		double sampSD = Double.parseDouble(text1.getText());
    		double sampMean = Double.parseDouble(text2.getText());
    		int n = Integer.parseInt(sizeText.getText());
    		double confidence = Double.parseDouble(confidenceText.getText());
    		if (sampSD < 0) {
    			throw new NumberFormatException();
    		}
    		double[] interval = new double[2];
    		ans.setFill(Color.BLACK);
    		if (sideChoice.getValue().equals("Two-Sided")) {
    			interval = TInterval(true, true, sampSD, sampMean, n, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[1]) + " <  μ  < " + df.format(interval[0]));
    		} else if (sideChoice.getValue().equals("Lower")) {
    			interval = TInterval(false, true, sampSD, sampMean, n, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[0]) + " < μ");
    		} else {
    			interval = TInterval(true, false, sampSD, sampMean, n, confidence);
    			ans.setVisible(true);
    			ans.setText("μ > " + df.format(interval[0]));
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

    void chiIntCalc() {
      	try {
    		double sampVar = Double.parseDouble(text1.getText());
    		int n = Integer.parseInt(sizeText.getText());
    		double confidence = Double.parseDouble(confidenceText.getText());
    		double[] interval = new double[2];
    		if (sampVar < 0) {
    			throw new NumberFormatException();
    		}
    		ans.setFill(Color.BLACK);
    		if (sideChoice.getValue().equals("Two-Sided")) {
    			interval = ChiInterval(true, true, sampVar, n, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[1]) + " <  σ^2  < " + df.format(interval[0]));
    		} else if (sideChoice.getValue().equals("Lower")) {
    			interval = ChiInterval(false, true, sampVar, n, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[0]) + " < σ^2");
    		} else {
    			interval = ChiInterval(true, false, sampVar, n, confidence);
    			ans.setVisible(true);
    			ans.setText("σ^2 > " + df.format(interval[0]));
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
    
    void propIntCalc() {
    	try {
    		double x = Double.parseDouble(text1.getText());
    		int n = Integer.parseInt(sizeText.getText());
    		if (n == 0) {
    			throw new ArithmeticException();
    		}
    		if (x < 0 || n < 0) {
    			throw new OutOfRangeException(x, 1, 100000);
    		}
    		if (x > n) {
    			throw new NumberFormatException();
    		}
    		double confidence = Double.parseDouble(confidenceText.getText());
    		double[] interval = new double[2];
    		ans.setFill(Color.BLACK);
    		if (sideChoice.getValue().equals("Two-Sided")) {
    			interval = PropInterval(true, true, x, n, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[1]) + " <  p  < " + df.format(interval[0]));
    		} else if (sideChoice.getValue().equals("Lower")) {
    			interval = PropInterval(false, true, x, n, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[0]) + " < p");
    		} else {
    			interval = PropInterval(true, false, x, n, confidence);
    			ans.setVisible(true);
    			ans.setText("p > " + df.format(interval[0]));
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