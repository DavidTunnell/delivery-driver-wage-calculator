import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class SQLInteract {

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String url = "jdbc:sqlite:src/saveTable.sqlite";
	// String user = "root";
	// String password = "root";

	ArrayList<String> sqlCol1 = new ArrayList<String>();
	ArrayList<String> sqlCol2 = new ArrayList<String>();
	ArrayList<String> sqlCol3 = new ArrayList<String>();
	ArrayList<String> sqlCol4 = new ArrayList<String>();
	ArrayList<String> sqlCol5 = new ArrayList<String>();
	ArrayList<String> sqlCol6 = new ArrayList<String>();
	ArrayList<String> sqlCol7 = new ArrayList<String>();
	String sqlArray[][] = new String[7][7];

	/*
	 * JTextField hourlyWageInput, tipArray[], hourArray[], gasArray[]; JLabel
	 * totalHours, totalHourlyEarnings, totalTips, weekPay, totalGas,
	 * avgGasLabel, wageArray[]; String otherArray[];
	 */

	public SQLInteract() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("select * from incomeCalc");
			rs = pst.executeQuery();

			while (rs.next()) {

				sqlCol1.add(rs.getString(1));
				int i1 = 0;
				for (String s : sqlCol1) {
					sqlArray[i1++][0] = s;
				}

				sqlCol2.add(rs.getString(2));
				int i2 = 0;
				for (String s : sqlCol2) {
					sqlArray[i2++][1] = s;
				}

				sqlCol3.add(rs.getString(3));
				int i3 = 0;
				for (String s : sqlCol3) {
					sqlArray[i3++][2] = s;
				}

				sqlCol4.add(rs.getString(4));
				int i4 = 0;
				for (String s : sqlCol4) {
					sqlArray[i4++][3] = s;
				}

				sqlCol5.add(rs.getString(5));
				int i5 = 0;
				for (String s : sqlCol5) {
					sqlArray[i5++][4] = s;
				}

				sqlCol6.add(rs.getString(6));
				int i6 = 0;
				for (String s : sqlCol6) {
					sqlArray[i6++][5] = s;
				}
				sqlCol7.add(rs.getString(7));
				int i7 = 0;
				for (String s : sqlCol7) {
					sqlArray[i7++][6] = s;
				}
			}
		}

		catch (Exception E) {
			System.out.println(E.getMessage());
		}
	}

	public SQLInteract(JTextField hourlyWageInput, JLabel totalHours,
			JLabel totalHourlyEarnings, JLabel totalTips, JLabel weekPay,
			JLabel totalGas, JLabel avgGasLabel, JTextField tipArray[],
			JTextField hourArray[], JTextField gasArray[], JLabel wageArray[]) {

		String noDollarSign[] = new String[7];
		String noDollarSign2[] = new String[7];
		String otherArray[] = { hourlyWageInput.getText(),
				totalHours.getText(), totalHourlyEarnings.getText(),
				totalTips.getText(), weekPay.getText(), totalGas.getText(),
				avgGasLabel.getText() };

		try {
			con = DriverManager.getConnection(url);
			Statement st = (Statement) con.createStatement();
			for (int i = 0; i <= 6; i++) {
				String tipToDb = "UPDATE incomeCalc SET TIPS = "
						+ tipArray[i].getText() + " WHERE ID = " + (i + 1);
				st.executeUpdate(tipToDb);
				String hourToDb = "UPDATE incomeCalc SET HOURS = "
						+ hourArray[i].getText() + " WHERE ID = " + (i + 1);
				st.executeUpdate(hourToDb);
				String gasToDb = "UPDATE incomeCalc SET GAS = "
						+ gasArray[i].getText() + " WHERE ID = " + (i + 1);
				st.executeUpdate(gasToDb);
				noDollarSign[i] = wageArray[i].getText().replace("$ ", "");
				String wageToDb = "UPDATE incomeCalc SET HOURLY = "
						+ noDollarSign[i] + " WHERE ID = " + (i + 1);
				st.executeUpdate(wageToDb);
				noDollarSign2[i] = otherArray[i].replace("$ ", "");
				String otherToDb = "UPDATE incomeCalc SET OTHER = "
						+ noDollarSign2[i] + " WHERE ID = " + (i + 1);
				st.executeUpdate(otherToDb);
			}
		}

		catch (Exception E) {
			System.out.println(E.getMessage());
		}
	}

}
