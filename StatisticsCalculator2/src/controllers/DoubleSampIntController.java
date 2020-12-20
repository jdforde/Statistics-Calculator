package controllers;

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

import auxstats.DoubleSampFuncs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class DoubleSampIntController extends DoubleSampFuncs {

	ObservableList<String> options = FXCollections.observableArrayList("Two-Sided", "Lower", "Upper");
	DecimalFormat df = new DecimalFormat("0.####");
	boolean zIntPressed = true;
	boolean tIntPressed;
	boolean fIntPressed;
	boolean propIntPressed;
	boolean yesPressed = true;
    
	@FXML
    private ChoiceBox<String> sideChoice;

    @FXML
    private RadioButton fInt;

    @FXML
    private TextField sampMean2text;

    @FXML
    private AnchorPane twoSampInt;

    @FXML
    private TextField popSD2text;

    @FXML
    private TextField popSD1text;

    @FXML
    private ToggleGroup pooled;

    @FXML
    private Text size1;

    @FXML
    private Text size2;

    @FXML
    private TextField confidenceText;

    @FXML
    private Text pooledText;

    @FXML
    private RadioButton propInt;

    @FXML
    private ToggleGroup group;

    @FXML
    private TextField size2text;

    @FXML
    private Text popSD1;

    @FXML
    private Text ans;

    @FXML
    private RadioButton zInt;

    @FXML
    private RadioButton yesPooled;

    @FXML
    private RadioButton tInt;

    @FXML
    private TextField size1text;

    @FXML
    private Text label2;

    @FXML
    private Text label11;

    @FXML
    private RadioButton noPooled;

    @FXML
    private Text sampMean1;

    @FXML
    private Button reset;

    @FXML
    private TextField sampMean1text;

    @FXML
    private Button calc;

    @FXML
    private Text sampMean2;

    @FXML
    private Text popSD2;

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
    	fIntPressed = false;
    	propIntPressed = false;
    	
    	pooledText.setVisible(false);
    	yesPooled.setVisible(false);
    	noPooled.setVisible(false);
    	
    	size1.setVisible(true);
    	size1text.setVisible(true);
    	size1text.setText("");
    	size2.setVisible(true);
    	size2text.setVisible(true);
    	size2text.setText("");
    	popSD1.setText("Population SD 1:");
    	popSD1text.setText("");
    	popSD2.setText("Population SD 2:");
    	popSD2text.setText("");
    	sampMean1.setText("Sample Mean 1:");
    	sampMean1text.setText("");
    	sampMean2.setText("Sample Mean 2:");
    	sampMean2text.setText("");
    	confidenceText.setText("");
    }

    @FXML
    void tIntPressed(ActionEvent event) {
    	ans.setVisible(false);
    	zIntPressed = false;
    	tIntPressed = true;
    	fIntPressed = false;
    	propIntPressed = false;
    	
    	pooledText.setVisible(true);
    	yesPooled.setVisible(true);
    	noPooled.setVisible(true);
    	
    	size1.setVisible(true);
    	size1text.setVisible(true);
    	size1text.setText("");
    	size2.setVisible(true);
    	size2text.setVisible(true);
    	size2text.setText("");
    	popSD1.setText("Sample SD 1:");
    	popSD1text.setText("");
    	popSD2.setText("Sample SD 2:");
    	popSD2text.setText("");
    	sampMean1.setText("Sample Mean 1:");
    	sampMean1text.setText("");
    	sampMean2.setText("Sample Mean 2:");
    	sampMean2text.setText("");
    	confidenceText.setText("");
    }

    @FXML
    void fIntPressed(ActionEvent event) {
    	ans.setVisible(false);
    	zIntPressed = false;
    	tIntPressed = false;
    	fIntPressed = true;
    	propIntPressed = false;
    	
    	pooledText.setVisible(false);
    	yesPooled.setVisible(false);
    	noPooled.setVisible(false);
    	
    	size1.setVisible(false);
    	size1text.setVisible(false);
    	size2.setVisible(false);
    	size2text.setVisible(false);
    	popSD1.setText("Size 1:");
    	popSD1text.setText("");
    	popSD2.setText("Size 2:");
    	popSD2text.setText("");
    	sampMean1.setText("Sample Variance 1:");
    	sampMean1text.setText("");
    	sampMean2.setText("Sample Variance 2:");
    	sampMean2text.setText("");
    	confidenceText.setText("");
    }

    @FXML
    void propIntPressed(ActionEvent event) {
    	ans.setVisible(false);
    	zIntPressed = false;
    	tIntPressed = false;
    	fIntPressed = false;
    	propIntPressed = true;
    	
    	pooledText.setVisible(false);
    	yesPooled.setVisible(false);
    	noPooled.setVisible(false);
    	
    	size1.setVisible(false);
    	size1text.setVisible(false);
    	size2.setVisible(false);
    	size2text.setVisible(false);
    	popSD1.setText("x1:");
    	popSD1text.setText("");
    	popSD2.setText("x2:");
    	popSD2text.setText("");
    	sampMean1.setText("n1:");
    	sampMean1text.setText("");
    	sampMean2.setText("n2:");
    	sampMean2text.setText("");
    	confidenceText.setText("");
    }

    @FXML
    void ResetClick(ActionEvent event) {
    	sideChoice.setValue("Two-Sided");
    	
    	if (zIntPressed) 
    		zIntPressed(event);
    	if (tIntPressed)
    		tIntPressed(event);
    	if (fIntPressed)
    		fIntPressed(event);
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
    	
    	if (fIntPressed) {
    		fIntCalc();
    	}
    	
    	if (propIntPressed) {
    		propIntCalc();
    	}
    }
    
    @FXML
    void yesPressed(ActionEvent event) {
    	yesPressed = true;
    }

    @FXML
    void noPressed(ActionEvent event) {
    	yesPressed = false;
    }
    
    
    void zIntCalc() {
    	try {
	    	int size1 = Integer.parseInt(size1text.getText());
	    	int size2 = Integer.parseInt(size2text.getText());
	    	double popSD1 = Double.parseDouble(popSD1text.getText());
	    	double popSD2 = Double.parseDouble(popSD2text.getText());
	    	double sampMean1 = Double.parseDouble(sampMean1text.getText());
	    	double sampMean2 = Double.parseDouble(sampMean2text.getText());
	    	double confidence = Double.parseDouble(confidenceText.getText());
	    	
	    	if (popSD1 < 0 || popSD2 < 0) {
    			throw new NotStrictlyPositiveException(popSD1);
    		}
    		if (size1 < 0 || size2 < 0) {
    			throw new NumberFormatException();
    		}
    		
    		double[] interval = new double[2];
    		ans.setFill(Color.BLACK);
    		if (sideChoice.getValue().equals("Two-Sided")) {
    			interval = ZInterval(true, true, size1, size2, popSD1, popSD2, sampMean1, sampMean2, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[1]) + " <  μ  < " + df.format(interval[0]));
    		} else if (sideChoice.getValue().equals("Lower")) {
    			interval = ZInterval(false, true, size1, size2, popSD1, popSD2, sampMean1, sampMean2, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[0]) + " < μ");
    		} else {
    			interval = ZInterval(true, false, size1, size2, popSD1, popSD2, sampMean1, sampMean2, confidence);
    			ans.setVisible(true);
    			ans.setText("μ > " + df.format(interval[0]));
    		}
	    	
	    	ans.setFill(Color.BLACK);
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
    		int size1 = Integer.parseInt(size1text.getText());
    		int size2 = Integer.parseInt(size2text.getText());
    		double sampSD1 = Double.parseDouble(popSD1text.getText());
    		double sampSD2 = Double.parseDouble(popSD2text.getText());
    		double sampMean1 = Double.parseDouble(sampMean1text.getText());
    		double sampMean2 = Double.parseDouble(sampMean2text.getText());
    		double confidence = Double.parseDouble(confidenceText.getText());
    		if (sampSD1 < 0 || sampSD2 < 0) {
    			throw new NumberFormatException();
    		}
    		ans.setFill(Color.BLACK);
    		double[] interval = new double[2];
    		//TIntervalUnpooled(boolean upper, boolean lower, int size1, int size2, double sampSD1, double sampSD2, double sampMean1,
			//double sampMean2, double confidence)
    		if (sideChoice.getValue().equals("Two-Sided")) {
    			if (yesPressed) {
    				interval = TIntervalPooled(true, true, size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, confidence);
    			} else {
    				interval = TIntervalUnpooled(true, true, size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, confidence);
    			}
    			ans.setVisible(true);
    			ans.setText(df.format(interval[1]) + " <  μ  < " + df.format(interval[0]));
    		} else if (sideChoice.getValue().equals("Lower")) {
    			if (yesPressed) {
    				interval = TIntervalPooled(false, true, size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, confidence);
    			} else {
    				interval = TIntervalUnpooled(false, true, size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, confidence);
    			}
    			ans.setVisible(true);
    			ans.setText(df.format(interval[0]) + " < μ");
    		} else {
    			if (yesPressed) {
    				interval = TIntervalPooled(true, false, size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, confidence);
    			} else {
    				interval = TIntervalUnpooled(true, false, size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, confidence);
    			}
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
    
    void fIntCalc() {
    	try {
    		int size1 = Integer.parseInt(popSD1text.getText());
    		int size2 = Integer.parseInt(popSD2text.getText());
    		double sampVar1 = Double.parseDouble(sampMean1text.getText());
    		double sampVar2 = Double.parseDouble(sampMean2text.getText());
    		double confidence = Double.parseDouble(confidenceText.getText());
    		
    		if (confidence < 0 || confidence > 1) {
    			throw new OutOfRangeException(confidence, 0, 1);
    		}
    		
    		if (sampVar1 < 0 || sampVar2 < 0) {
    			throw new NumberFormatException();
    		}
    		
    		ans.setFill(Color.BLACK);
    		double[] interval = new double[2];
    		if (sideChoice.getValue().equals("Two-Sided")) {
    			interval = FInterval(true, true, size1, size2, sampVar1, sampVar2, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[1]) + " <  σ^2  < " + df.format(interval[0]));
    		} else if (sideChoice.getValue().equals("Lower")) {
    			interval = FInterval(false, true, size1, size2, sampVar1, sampVar2, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[0]) + " < σ^2");
    		} else {
    			interval = FInterval(true, false, size1, size2, sampVar1, sampVar2, confidence);
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
    		int x1 = Integer.parseInt(popSD1text.getText());
    		int x2 = Integer.parseInt(popSD2text.getText());
    		int n1 = Integer.parseInt(sampMean1text.getText());
    		int n2 = Integer.parseInt(sampMean2text.getText());
    		if (n1 == 0 || n2 == 0) {
    			throw new ArithmeticException();
    		}
    		if (x1 < 0 || n1 < 0 || x2 < 0 || n2 < 0) {
    			throw new OutOfRangeException(x1, 1, 100000);
    		}
    		if (x1 > n1 || x2 > n2) {
    			throw new NumberFormatException();
    		}
    		double confidence = Double.parseDouble(confidenceText.getText());
    		double[] interval = new double[2];
    		
    		ans.setFill(Color.BLACK);
    		if (sideChoice.getValue().equals("Two-Sided")) {
    			interval = PropInterval(true, true, x1, x2, n1, n2, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[1]) + " <  p  < " + df.format(interval[0]));
    		} else if (sideChoice.getValue().equals("Lower")) {
    			interval = PropInterval(false, true, x1, x2, n1, n2, confidence);
    			ans.setVisible(true);
    			ans.setText(df.format(interval[0]) + " < p");
    		} else {
    			interval = PropInterval(true, false, x1, x2, n1, n2, confidence);
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

