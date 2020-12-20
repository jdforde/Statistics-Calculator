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

public class DoubleSampHypController extends DoubleSampFuncs{

	ObservableList<String> meanOptions = FXCollections.observableArrayList("μ1 ≠ μ2", "μ1 < μ2", "μ1 > μ2");
	ObservableList<String> varOptions = FXCollections.observableArrayList("σ²1 ≠ σ²2", "σ²1 < σ²2", "σ²1 > σ²2");
	ObservableList<String> propOptions = FXCollections.observableArrayList("p1 ≠ p2", "p1 < p2", "p1 > p2");
	DecimalFormat df = new DecimalFormat("0.####");
	boolean zTestPressed = true;
	boolean tTestPressed;
	boolean fTestPressed;
	boolean propTestPressed;
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
    	sideChoice.setItems(meanOptions);
    	sideChoice.setValue("μ1 ≠ μ2");
    	//needs to be Verdana like everything else
    }

    @FXML
    void zTestPressed(ActionEvent event) {
    	ans.setVisible(false);
    	zTestPressed = true;
    	tTestPressed = false;
    	fTestPressed = false;
    	propTestPressed = false;
    	sideChoice.setItems(meanOptions);
    	sideChoice.setValue("μ1 ≠ μ2");
    	
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
    }

    @FXML
    void tTestPressed(ActionEvent event) {
    	ans.setVisible(false);
    	zTestPressed = false;
    	tTestPressed = true;
    	fTestPressed = false;
    	propTestPressed = false;
    	sideChoice.setItems(meanOptions);
    	sideChoice.setValue("μ1 ≠ μ2");
    	
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
    }

    @FXML
    void fTestPressed(ActionEvent event) {
    	ans.setVisible(false);
    	zTestPressed = false;
    	tTestPressed = false;
    	fTestPressed = true;
    	propTestPressed = false;
    	
    	pooledText.setVisible(false);
    	yesPooled.setVisible(false);
    	noPooled.setVisible(false);
    	sideChoice.setItems(varOptions);
    	sideChoice.setValue("σ²1 ≠ σ²2");
    	
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
    }

    @FXML
    void propTestPressed(ActionEvent event) {
    	ans.setVisible(false);
    	zTestPressed = false;
    	tTestPressed = false;
    	fTestPressed = false;
    	propTestPressed = true;
    	sideChoice.setItems(propOptions);
    	sideChoice.setValue("p1 ≠ p2");
    	
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
    }

    @FXML
    void yesPressed(ActionEvent event) {
    	yesPressed = true;
    }

    @FXML
    void noPressed(ActionEvent event) {
    	yesPressed = false;
    }

    @FXML
    void ResetClick(ActionEvent event) {
    	if (zTestPressed) 
    		zTestPressed(event);
    	if (tTestPressed)
    		tTestPressed(event);
    	if (fTestPressed)
    		fTestPressed(event);
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
    	
    	if (fTestPressed) {
    		fTestCalc();
    	}
    	
    	if (propTestPressed) {
    		propTestCalc();
    	}
    }
    
    void zTestCalc() {
    	try {
    		int n1 = Integer.parseInt(size1text.getText());
    		int n2 = Integer.parseInt(size2text.getText());
    		double popSD1 = Double.parseDouble(popSD1text.getText());
    		double popSD2 = Double.parseDouble(popSD2text.getText());
    		double sampMean1 = Double.parseDouble(sampMean1text.getText());
    		double sampMean2 = Double.parseDouble(sampMean2text.getText());
    		ans.setFill(Color.BLACK);
    		if (popSD1 < 0 || popSD2 < 0) {
    			throw new NotStrictlyPositiveException(popSD1);
    		}
    		if (n1 < 0 || n2 < 0) {
    			throw new NumberFormatException();
    		}
    		//double n1, double n2, double popSD1, double popSD2, double sampMean1, double sampMean2, int test) {
    		if (sideChoice.getValue().equals("μ1 ≠ μ1")) {
    			double pval = ZTest(n1, n2, popSD1, popSD2, sampMean1, sampMean2, 0);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else if (sideChoice.getValue().equals("μ1 < μ2")) {
    			double pval = ZTest(n1, n2, popSD1, popSD2, sampMean1, sampMean2, 1);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else {
    			double pval = ZTest(n1, n2, popSD1, popSD2, sampMean1, sampMean2, 2);
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
    		int size1 = Integer.parseInt(size1text.getText());
    		int size2 = Integer.parseInt(size2text.getText());
    		double sampSD1 = Double.parseDouble(popSD1text.getText());
    		double sampSD2 = Double.parseDouble(popSD2text.getText());
    		double sampMean1 = Double.parseDouble(sampMean1text.getText());
    		double sampMean2 = Double.parseDouble(sampMean2text.getText());
    		ans.setFill(Color.BLACK);
    		if (sampSD1 < 0 || sampSD2 < 0) {
    			throw new NumberFormatException();
    		}
    		double pval = 0;
    		if (sideChoice.getValue().equals("μ1 ≠ μ2")) {
    			if (yesPressed) {
    				pval = TTestPooled(size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, 0);
    				
    			} else {
    				pval = TTestUnpooled(size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, 0);
    			}
    			
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else if (sideChoice.getValue().equals("μ1 < μ2")) {
    			if (yesPressed) {
    				pval = TTestPooled(size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, 1);
    			} else {
    				pval = TTestUnpooled(size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, 1);
    			}
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else {
    			if (yesPressed) {
    				pval = TTestPooled(size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, 2);
    			} else {
    				pval = TTestUnpooled(size1, size2, sampSD1, sampSD2, sampMean1, sampMean2, 0);
    			}
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
    
    void fTestCalc() {
    	try {
    		int size1 = Integer.parseInt(popSD1text.getText());
    		int size2 = Integer.parseInt(popSD2text.getText());
    		double sampVar1 = Double.parseDouble(sampMean1text.getText());
    		double sampVar2 = Double.parseDouble(sampMean2text.getText());
    		
    		if (sampVar1 < 0 || sampVar2 < 0) {
    			throw new NumberFormatException();
    		}
    		
    		ans.setFill(Color.BLACK);
    		
    		if (sideChoice.getValue().equals("σ²1 ≠ σ²2")) {
    			double pval = FTest(size1, size2, sampVar1, sampVar2, 0);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else if (sideChoice.getValue().equals("σ²1 > σ²2")) {
    			double pval = FTest(size1, size2, sampVar1, sampVar2, 1);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else {
    			double pval = FTest(size1, size2, sampVar1, sampVar2, 2);
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
    	
    		
    		ans.setFill(Color.BLACK);
    		if (sideChoice.getValue().equals("p1 ≠ p2")) {
    			double pval = PropTest(x1, x2, n1, n2, 0);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else if (sideChoice.getValue().equals("p1 > p2")) {
    			double pval = PropTest(x1, x2, n1, n2, 1);
    			ans.setVisible(true);
    			ans.setText("P-value: " + df.format(pval));
    		} else {
    			double pval = PropTest(x1, x2, n1, n2, 2);
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
    
   
