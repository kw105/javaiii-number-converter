import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.regex.*;

/**
 * Menu class
 * @author Kevin
 *
 */

public class Menu implements ActionListener {

	
	private JFrame frame;
	private JPanel panel;
	private JButton binToDecButton;
	private JButton binToHexButton;
	private JButton decToBinButton;
	private JButton decToHexButton;
	private JButton hexToDecButton;
	private JButton hexToBinButton;
	
	private Binary bin;

	private Decimal dec;
	
	private Hexadecimal hex;
	
	/**
	 * Display the menu
	 */
	public void display() {
		frame = new JFrame();
		panel = new JPanel();
		binToDecButton = new JButton();
		binToHexButton = new JButton();
		decToBinButton = new JButton();
		decToHexButton = new JButton();
		hexToDecButton = new JButton();
		hexToBinButton = new JButton();
		
		frame.setVisible(true);
		frame.add(panel);
		panel.add(binToDecButton);
		panel.add(binToHexButton);
		panel.add(decToBinButton);
		panel.add(decToHexButton);
		panel.add(hexToBinButton);
		panel.add(hexToDecButton);
		
		binToDecButton.setText("Bin to Dec");
		binToHexButton.setText("Bin to Hex");
		decToBinButton.setText("Dec to Bin");
		decToHexButton.setText("Dec to Hex");
		hexToBinButton.setText("Hex to Bin");
		hexToDecButton.setText("Hex to Dec");
		
		frame.pack();
		binToDecButton.addActionListener(this);
		binToHexButton.addActionListener(this);
		decToBinButton.addActionListener(this);
		decToHexButton.addActionListener(this);
		hexToBinButton.addActionListener(this);
		hexToDecButton.addActionListener(this);
	}
	
	/**
	 * Perform an action when there is user input from the menu window
	 * @param ActionEvent action event
	 */
	@Override
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == binToDecButton) {
			String inputValue = JOptionPane.showInputDialog("Please enter a binary number");
			if (inputValue != null) {
				String inputValue2 = inputValue.trim();
				if (isValidNumber(inputValue2)) {
					if (isValidBinaryNumber(inputValue2)) {
						bin = new Binary(removePositiveSignIfPresent(inputValue2));
						bin.convertToDecimal();
						JOptionPane.showMessageDialog(null, "The decimal value of "+inputValue2+" (binary) is: " + bin.getDecVal());
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid binary number. Please try again.");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid number. Please try again.");
				}
			}
		}
		if (e.getSource() == binToHexButton) {
			String inputValue = JOptionPane.showInputDialog("Please enter a binary number");
			if (inputValue != null) {
				String inputValue2 = inputValue.trim();
				if (isValidNumber(inputValue2)) {
					if (isValidBinaryNumber(inputValue2)) {
						bin = new Binary(removePositiveSignIfPresent(inputValue2));
						bin.convertToHexadecimal();
						JOptionPane.showMessageDialog(null, "The hexadecimal value of "+inputValue2+" (binary) is: " + bin.getHexVal());
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid binary number. Please try again.");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid number. Please try again.");
				}
			}
		}
		if (e.getSource() == decToBinButton) {
			String inputValue = JOptionPane.showInputDialog("Please enter a decimal number");
			if (inputValue != null) {
				String inputValue2 = inputValue.trim();
				if (isValidNumber(inputValue2)) {
					if (isValidDecimalNumber(inputValue2)) {
						dec = new Decimal(removePositiveSignIfPresent(inputValue2));
						dec.convertToBinary();
						JOptionPane.showMessageDialog(null, "The binary value of "+inputValue2+" (decimal) is: " + dec.getBinVal());
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid decimal number. Please try again.");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid number. Please try again.");
				}
			}
		}
		if (e.getSource() == decToHexButton) {
			String inputValue = JOptionPane.showInputDialog("Please enter a decimal number");
			if (inputValue != null) {
				String inputValue2 = inputValue.trim();
				if (isValidNumber(inputValue2)) {
					if (isValidDecimalNumber(inputValue2)) {
						dec = new Decimal(removePositiveSignIfPresent(inputValue2));
						dec.convertToHexadecimal();
						JOptionPane.showMessageDialog(null, "The hexadecimal value of "+inputValue2+" (decimal) is: " + dec.getHexVal());
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid decimal number. Please try again.");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid number. Please try again.");
				}
			}
		}
		if (e.getSource() == hexToDecButton) {
			String inputValue = JOptionPane.showInputDialog("Please enter a hexadecimal number");
			if (inputValue != null) {
				String inputValue2 = inputValue.trim();
				if (isValidNumber(inputValue2)) {
					hex = new Hexadecimal(removePositiveSignIfPresent(inputValue2));
					hex.convertToDecimal();
					JOptionPane.showMessageDialog(null, "The decimal value of "+inputValue2+" (hexadecimal) is: " + hex.getDecVal());
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid number. Please try again.");
				}
			}
		}
		if (e.getSource() == hexToBinButton) {
			String inputValue = JOptionPane.showInputDialog("Please enter a hexadecimal number");
			if (inputValue != null) {
				String inputValue2 = inputValue.trim();
				if (isValidNumber(inputValue2)) {
					hex = new Hexadecimal(removePositiveSignIfPresent(inputValue2));
					hex.convertToBinary();
					JOptionPane.showMessageDialog(null, "The binary value of "+inputValue2+" (hexadecimal) is: " + hex.getBinVal());
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid number. Please try again.");
				}
			}
		}
	}

	/**
	 * Trim the leading zeros for the final converted number
	 * @param s Number value to be trimmed
	 * @return Final converted number with leading zeros trimmed
	 */
	public static String trimLeadingZeros(String s) {
		String s1 = new String(s);
		while (s1.length() > 1) {
			if (s1.charAt(0) == '0') {
				s1 = s1.substring(1,s1.length());
			}
			else {
				return s1;
			}
		}
		return s1;
	}
	
	/**
	 * Test if a string represents a negative number
	 * @param s input string to be tested
	 * @return true if it represents a negative number. False otherwise.
	 */
	public static boolean isNegative(String s) {
		if ( (s != null) && (s.charAt(0) == '-') ) {
			return true;
		}
		return false;
	}
	
	/**
	 * Remove the + sign (if present) at the beginning of the number that user entered
	 * @param s input number
	 * @return a number string with the + sign (if present at the beginning of the string) removed.
	 */
	private static String removePositiveSignIfPresent (String s) {
		if ( (s != null) && (s.charAt(0) == '+') ) {
			return s.substring(1);
		}
		return s;
	}
	
	/**
	 * Test if a string represents a valid number (binary, decimal or hexadecimal)
	 * @param s input string
	 * @return true if the string represents a valid number. false otherwise.
	 */
	private static boolean isValidNumber(String s) {
		Pattern ptn = Pattern.compile("^[+-]?[0-9A-F]+$");
		Matcher mt = ptn.matcher(s);
		boolean result = mt.matches();
		return result;
	}

	/**
	 * Test if a string represents a valid binary number
	 * @param s input string
	 * @return true if the string represents a valid binary number. false otherwise.
	 */
	private static boolean isValidBinaryNumber(String s) {
		Pattern ptn = Pattern.compile("^[+-]?[01]+$");
		Matcher mt = ptn.matcher(s);
		boolean result = mt.matches();
		return result;
	}
	
	/**
	 * Test if a string represents a valid decimal number
	 * @param s input string
	 * @return true if the string represents a valid decimal number. false otherwise.
	 */
	private static boolean isValidDecimalNumber(String s) {
		Pattern ptn = Pattern.compile("^[+-]?[0-9]+$");
		Matcher mt = ptn.matcher(s);
		boolean result = mt.matches();
		return result;
	}

}
