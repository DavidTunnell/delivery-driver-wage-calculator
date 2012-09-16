import javax.swing.JTextField;

public class Calculations {
	private JTextField hourlyWageInput;
	private JTextField tipArray[] = new JTextField[7];
	private JTextField hourArray[] = new JTextField[7];
	private JTextField gasArray[] = new JTextField[7];

	public Calculations() {
	}

	public Calculations(JTextField[] tipArray, JTextField[] hourArray,
			JTextField hourlyWageInput, JTextField[] gasArray) {
		this.tipArray = tipArray;
		this.hourArray = hourArray;
		this.hourlyWageInput = hourlyWageInput;
		this.gasArray = gasArray;
	}

	public String[] calculateHourlyEarnings() {
		String[] wageArrayStrings = new String[7];
		for (int i = 0; i < 7; i++) {
			double tip = Double.parseDouble(tipArray[i].getText());
			double hour = Double.parseDouble(hourArray[i].getText());
			double hourly = Double.parseDouble(hourlyWageInput.getText());

			if (hour != 0) {
				wageArrayStrings[i] = "$ "
						+ String.format("%.2f", (hourly * hour + tip) / hour);
			} else {
				wageArrayStrings[i] = ("$ 0.00");
			}
		}
		return wageArrayStrings;
	}

	public String calculateHours() {
		double sum = 0;
		for (int i = 0; i < 7; i++) {
			double hour = Double.parseDouble(hourArray[i].getText());
			sum += hour;
		}
		return String.format("%.2f", sum);
	}

	public String calculateAvgHourly() {
		String avgHourlyString;
		double sumHours = 0, sumTips = 0, hourlyWage = 0;
		double tips, hours, avgHourlyDouble;

		for (int i = 0; i < 7; i++) {
			tips = Double.parseDouble(tipArray[i].getText());
			sumTips += tips;
			hours = Double.parseDouble(hourArray[i].getText());
			sumHours += hours;
			hourlyWage = Double.parseDouble(hourlyWageInput.getText());
		}
		if (sumHours == 0) {
			return "$ 0.00";
		} else {
			avgHourlyDouble = (hourlyWage * sumHours + sumTips) / sumHours;
			avgHourlyString = "$ " + String.format("%.2f", avgHourlyDouble);
			return avgHourlyString;
		}
	}

	public String calculateTotalTips() {
		String totalTips;
		double sum = 0;
		for (int i = 0; i < 7; i++) {
			double tip = Double.parseDouble(tipArray[i].getText());
			sum += tip;
		}
		totalTips = "$ " + String.format("%.2f", sum);
		return totalTips;
	}

	public String calculateTotalIncome() {
		String totalTotalString;
		double sumHours = 0, sumTips = 0, hourlyWage = 0, totalTotalDouble;
		double tips, hours;
		for (int i = 0; i < 7; i++) {
			tips = Double.parseDouble(tipArray[i].getText());
			sumTips += tips;
			hours = Double.parseDouble(hourArray[i].getText());
			sumHours += hours;
			hourlyWage = Double.parseDouble(hourlyWageInput.getText());
		}
		totalTotalDouble = (hourlyWage * sumHours + sumTips);
		totalTotalString = "$ " + String.format("%.2f", totalTotalDouble);
		return totalTotalString;
	}

	public String calculateGasTotal() {
		String totalGasString;
		double sumGas = 0;
		double gas;
		for (int i = 0; i < 7; i++) {
			gas = Double.parseDouble(gasArray[i].getText());
			sumGas += gas;
		}
		totalGasString = "$ " + String.format("%.2f", sumGas);
		return totalGasString;
	}

	public String calculateHourlyLessGas() {
		String avgHourlyString;
		double sumHours = 0, sumTips = 0, sumGas = 0, hourlyWage = 0;
		double tips, hours, avgHourlyDouble, gas;
		for (int i = 0; i < 7; i++) {
			tips = Double.parseDouble(tipArray[i].getText());
			sumTips += tips;
			hours = Double.parseDouble(hourArray[i].getText());
			sumHours += hours;
			hourlyWage = Double.parseDouble(hourlyWageInput.getText());
			gas = Double.parseDouble(gasArray[i].getText());
			sumGas += gas;
		}
		if (sumHours == 0) {
			return "$ 0.00";
		} else {
			avgHourlyDouble = (hourlyWage * sumHours + sumTips - sumGas)
					/ sumHours;
			avgHourlyString = "$ " + String.format("%.2f", avgHourlyDouble);
			return avgHourlyString;
		}
	}
}
