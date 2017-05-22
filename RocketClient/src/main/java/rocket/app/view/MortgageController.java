package rocket.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import exceptions.RateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController implements Initializable {


	@FXML TextField txtCreditScore;
	@FXML TextField txtMortgagePayment;
	@FXML TextField txtIncome;
	@FXML TextField txtExpenses;
	@FXML TextField txtHouseCost;
	@FXML TextField txtDownPayment;	
	
	@FXML
	private ComboBox cmbTerm;
	
	private TextField txtNew;
	
	private MainApp mainApp;
	
	
	@FXML
	private Label CreditScoreLabel;
	@FXML
	private Label HouseCostLabel;
	@FXML
	private Label ExpensesLabel;
	@FXML
	private Label TermLabel;
	@FXML
	private Label IncomeLabel;
	@FXML
	private Label DownPaymentLabel;
	@FXML
	private Label MortgagePaymentLabel;
	@FXML
	private Button btnCalculate;
	@FXML
	private Label showErrorMessagesLabel;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	    cmbTerm.getItems().addAll("15", "30");
	   
	}

	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		boolean goOn = true;
		Action calculate_payment = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		double rate = 0;
		int credScore = Integer.parseInt(txtCreditScore.getText());
		try
		{			
			rate = (RateBLL.getRate(credScore));
		}
		catch(RateException e)
		{
			goOn = false;
			showErrorMessagesLabel.setText("Credit score is too low.");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Credit Score failure");
			alert.setHeaderText("Credit score is too low.");
			alert.showAndWait().ifPresent(rs -> {
			    if (rs == ButtonType.OK) {
			        System.out.println("Pressed OK.");
			    }
			});
		}
		lq.setIncome(Double.parseDouble(txtIncome.getText()));

		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));

		lq.setiTerm(Integer.parseInt(cmbTerm.getValue().toString()));
	
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
				
		lq.setdAmount( Double.parseDouble(txtHouseCost.getText()));
		
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		
		lq.setdRate(rate);

		calculate_payment.setLoanRequest(lq);
				
		mainApp.messageSend(lq);
	}
    

	@FXML
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		txtMortgagePayment.setText( String.format("%1$,.2f", Math.abs(lRequest.getdPayment())));
		
	}

}
