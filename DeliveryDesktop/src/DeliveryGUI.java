import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

public class DeliveryGUI extends JFrame {

	public JLabel hourlyWage, blank, labelRow, totalTips, totalHours,
			totalHourlyEarnings, totalPay, weekPay, day, totalGas,
			totalHoursLabel, totalTipsLabel, totalGasLabel, totalWageLabel,
			avgGas, avgGasLabel;
	public JTextField hourlyWageInput;
	public JButton saveComp;

	public double[] incomeArray = new double[7];
	public JTextField[] tipArray = new JTextField[7];
	public JTextField[] hourArray = new JTextField[7];
	public JTextField[] gasArray = new JTextField[7];
	public JLabel[] wageArray = new JLabel[7];
	public String[] dayArray = { "Monday", "Tuesday", "Wednesday", "Thursday",
			"Friday", "Saturday", "Sunday" };

	JPanel p1 = new JPanel(new GridLayout(0, 5));

	public DeliveryGUI() {
		SQLInteract sqlObject = new SQLInteract();
		DocumentFilter invalidFilter = new docFilter();

		hourlyWage = new JLabel("Hourly Wage: ");
		hourlyWage.setHorizontalAlignment(JLabel.CENTER);
		p1.add(hourlyWage);
		hourlyWageInput = new JTextField(sqlObject.sqlArray[0][5]);
		((AbstractDocument) hourlyWageInput.getDocument())
				.setDocumentFilter(invalidFilter);
		hourlyWageInput.setHorizontalAlignment(JTextField.CENTER);
		p1.add(hourlyWageInput);

		blankLabels(3);

		labelRow = new JLabel("Day of the Week");
		labelRow.setHorizontalAlignment(JLabel.CENTER);
		p1.add(labelRow);
		labelRow = new JLabel("Tips");
		labelRow.setHorizontalAlignment(JLabel.CENTER);
		p1.add(labelRow);
		labelRow = new JLabel("Hours Worked");
		labelRow.setHorizontalAlignment(JLabel.CENTER);
		p1.add(labelRow);
		labelRow = new JLabel("Gas");
		labelRow.setHorizontalAlignment(JLabel.CENTER);
		p1.add(labelRow);
		labelRow = new JLabel("Hourly Earnings");
		labelRow.setHorizontalAlignment(JLabel.CENTER);
		p1.add(labelRow);

		for (int i = 0; i <= 6; i++) {
			day = new JLabel(dayArray[i]);
			day.setHorizontalAlignment(JTextField.CENTER);
			p1.add(day);
			tipArray[i] = new JTextField(sqlObject.sqlArray[i][1]);
			((AbstractDocument) tipArray[i].getDocument())
					.setDocumentFilter(invalidFilter);
			tipArray[i].setHorizontalAlignment(JTextField.CENTER);
			p1.add(tipArray[i]);
			hourArray[i] = new JTextField(sqlObject.sqlArray[i][2]);
			((AbstractDocument) hourArray[i].getDocument())
					.setDocumentFilter(invalidFilter);
			hourArray[i].setHorizontalAlignment(JTextField.CENTER);
			p1.add(hourArray[i]);
			gasArray[i] = new JTextField(sqlObject.sqlArray[i][3]);
			((AbstractDocument) gasArray[i].getDocument())
					.setDocumentFilter(invalidFilter);
			gasArray[i].setHorizontalAlignment(JTextField.CENTER);
			p1.add(gasArray[i]);
			wageArray[i] = new JLabel("$ "
					+ String.format("%.2f",
							Double.parseDouble(sqlObject.sqlArray[i][4])));
			wageArray[i].setHorizontalAlignment(JTextField.CENTER);
			p1.add(wageArray[i]);
		}

		blankLabels(12);

		totalHoursLabel = new JLabel("Total Hours:");
		totalHoursLabel.setHorizontalAlignment(JLabel.CENTER);
		p1.add(totalHoursLabel);

		totalHours = new JLabel(String.format("%.2f",
				Double.parseDouble(sqlObject.sqlArray[1][5])));
		totalHours.setHorizontalAlignment(JLabel.CENTER);
		p1.add(totalHours);

		blankLabels(3);

		totalWageLabel = new JLabel("Average Hourly:");
		totalWageLabel.setHorizontalAlignment(JLabel.CENTER);
		p1.add(totalWageLabel);
		totalHourlyEarnings = new JLabel("$ "
				+ String.format("%.2f",
						Double.parseDouble(sqlObject.sqlArray[2][5])));
		totalHourlyEarnings.setHorizontalAlignment(JLabel.CENTER);
		p1.add(totalHourlyEarnings);

		blankLabels(3);

		totalTipsLabel = new JLabel("Total Tips:");
		totalTipsLabel.setHorizontalAlignment(JLabel.CENTER);
		p1.add(totalTipsLabel);
		totalTips = new JLabel("$ "
				+ String.format("%.2f",
						Double.parseDouble(sqlObject.sqlArray[3][5])));
		totalTips.setHorizontalAlignment(JLabel.CENTER);
		p1.add(totalTips);

		blankLabels(3);

		totalPay = new JLabel("Total Income:");
		totalPay.setHorizontalAlignment(JLabel.CENTER);
		p1.add(totalPay);
		weekPay = new JLabel("$ "
				+ String.format("%.2f",
						Double.parseDouble(sqlObject.sqlArray[4][5])));
		weekPay.setHorizontalAlignment(JLabel.CENTER);
		p1.add(weekPay);

		blankLabels(3);

		totalGasLabel = new JLabel("Total Gas Cost:");
		totalGasLabel.setHorizontalAlignment(JLabel.CENTER);
		p1.add(totalGasLabel);
		totalGas = new JLabel("$ "
				+ String.format("%.2f",
						Double.parseDouble(sqlObject.sqlArray[5][5])));
		totalGas.setHorizontalAlignment(JLabel.CENTER);
		p1.add(totalGas);

		blankLabels(3);

		avgGas = new JLabel("Hourly Less Gas:");
		avgGas.setHorizontalAlignment(JLabel.CENTER);
		p1.add(avgGas);
		avgGasLabel = new JLabel("$ "
				+ String.format("%.2f",
						Double.parseDouble(sqlObject.sqlArray[6][5])));
		avgGasLabel.setHorizontalAlignment(JLabel.CENTER);
		p1.add(avgGasLabel);

		blankLabels(8);

		saveComp = new JButton("Compute/Save");
		p1.add(saveComp);
		saveComp.addActionListener(new ButtonListener());
		blankLabels(1);

		// add panel to frame
		add(p1);
	}

	private void blankLabels(int rows) {
		for (int i = 1; i <= rows; i++) {
			p1.add(new JLabel());
		}
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Calculations actionObject = new Calculations(tipArray, hourArray,
					hourlyWageInput, gasArray);

			String[] hourlyEarnings = actionObject.calculateHourlyEarnings();
			for (int i = 0; i <= 6; i++) {
				wageArray[i].setText(hourlyEarnings[i]);
			}

			String hoursJLabel = actionObject.calculateHours();
			totalHours.setText(hoursJLabel);

			String hourlyJLabel = actionObject.calculateAvgHourly();
			totalHourlyEarnings.setText(hourlyJLabel);

			String totalTipsJLabel = actionObject.calculateTotalTips();
			totalTips.setText(totalTipsJLabel);

			String totalIncomeJLabel = actionObject.calculateTotalIncome();
			weekPay.setText(totalIncomeJLabel);

			String totalGasJLabel = actionObject.calculateGasTotal();
			totalGas.setText(totalGasJLabel);

			String hourlyLessGasJLabel = actionObject.calculateHourlyLessGas();
			avgGasLabel.setText(hourlyLessGasJLabel);

			new SQLInteract(hourlyWageInput, totalHours, totalHourlyEarnings,
					totalTips, weekPay, totalGas, avgGasLabel, tipArray,
					hourArray, gasArray, wageArray);
		}
	}

	public static void main(String[] args) {
		DeliveryGUI frame = new DeliveryGUI();
		frame.setTitle("Delivery Driver Wage Calculator v1.0");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
