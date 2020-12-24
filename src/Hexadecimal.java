/**
 * Hexadecial class
 * @author Kevin
 *
 */
public class Hexadecimal {

	private String decVal;
	private String binVal;
	private String hexVal;
	
	/**
	 * Default constructor
	 */
	public Hexadecimal() {
		this("", "", "");
	}
	
	/**
	 * Custom constructor
	 * @param decVal dec val
	 * @param binVal bin val
	 * @param hexVal hex val
	 */
	public Hexadecimal(String decVal, String binVal, String hexVal) {
		this.decVal = decVal;
		this.binVal = binVal;
		this.hexVal = hexVal;
	}
	
	/**
	 * Custom constructor
	 * @param hexVal hex val
	 */
	public Hexadecimal(String hexVal) {
		this.decVal = "";
		this.binVal = "";
		this.hexVal = hexVal;
	}
	
	/**
	 * Getter method for binVal
	 * @return binVal
	 */
	public String getBinVal() {
		return binVal;
	}

	/**
	 * Setter method for binVal
	 * @param binVal bin val
	 */
	public void setBinVal(String binVal) {
		this.binVal = binVal;
	}
	
	/**
	 * Getter method for decVal
	 * @return decVal
	 */
	public String getDecVal() {
		return decVal;
	}

	/**
	 * Setter method for decVal
	 * @param decVal dec val
	 */
	public void setDecVal(String decVal) {
		this.decVal = decVal;
	}

	/**
	 * Getter method for hexVal
	 * @return hex val
	 */
	public String getHexVal() {
		return hexVal;
	}

	/**
	 * Setter method for hexVal
	 * @param hexVal hex val
	 */
	public void setHexVal(String hexVal) {
		this.hexVal = hexVal;
	}

	/**
	 * Convert hex to binary. Set binVal.
	 */
	public void convertToBinary() {
		if (hexVal != null) {
			String hexVal_temp = new String(hexVal);
			String numberSign = "";
			if (Menu.isNegative(hexVal)) {
				hexVal_temp = hexVal.substring(1);
				numberSign = "-";
			}
			int digits = hexVal_temp.length();
			//int finalVal = 0;
			for (int i = 0; i < digits; i++) {
				//int intVal = getDecValueOfHexDigit(hexVal_temp.charAt(i));
				//finalVal += intVal * Math.pow(16, digits-i-1);
				binVal = binVal + getBinaryValuesOfHexDigit(hexVal_temp.charAt(i));
			}
			if (numberSign.equals("-")) {
				binVal = twosComplement("0"+Menu.trimLeadingZeros(binVal+""));
			}
			else {
				binVal = "0" + Menu.trimLeadingZeros(binVal+"");
			}
		}
		//decVal = trimLeadingZeros(decVal);
	}

	/**
	 * Convert hex to decimal. Set decVal.
	 */
	public void convertToDecimal() {
		if (hexVal != null) {
			String hexVal_temp = new String(hexVal);
			String numberSign = "";
			if (Menu.isNegative(hexVal)) {
				hexVal_temp = hexVal.substring(1);
				numberSign = "-";
			}
			int digits = hexVal_temp.length();
			int finalVal = 0;
			for (int i = 0; i < digits; i++) {
				int intVal = getDecValueOfHexDigit(hexVal_temp.charAt(i));
				finalVal += intVal * Math.pow(16, digits-i-1);
			}
			decVal = numberSign + Menu.trimLeadingZeros(finalVal+"");
		}
		//decVal = trimLeadingZeros(decVal);
	}

	/**
	 * Return the twos complement of a binary number
	 * @param s a positive binary number
	 * @return Twos complement of the binary number
	 */
	private static String twosComplement (String s) {
		// Invert the binary number digits
		String sInverted = "";
		for (int i = 0; i < s.length(); i++) {
			char c = 0;
			if (s.charAt(i) == '1') {
				c = '0';
			}
			else {
				c = '1';
			}
			sInverted = sInverted + c;
		}
		
		String sInvertedPlusOne = "";
		
		boolean riseOne = false;
		int numChars = sInverted.length();
		for (int j = (numChars-1); j >= 0; j--) {
			char c = sInverted.charAt(j);
			if (j == (numChars-1)) {
				if (c=='0') {
					sInvertedPlusOne = sInverted.substring(0,j) + '1';
					return sInvertedPlusOne;
				}
				else {
					sInvertedPlusOne = "0";
					riseOne = true;
				}
			}
			else {
				if (c=='0') {
					if (riseOne) {
						sInvertedPlusOne = sInverted.substring(0,j) + '1' + sInvertedPlusOne;
					}
					else {
						sInvertedPlusOne = sInverted.substring(0,j) + '0' + sInvertedPlusOne;
					}
					return sInvertedPlusOne;
				}
				else {
					if (riseOne) {
						sInvertedPlusOne = '0' + sInvertedPlusOne;
						riseOne = true;
					}
					else {
						sInvertedPlusOne = sInverted.substring(0,j) + '1' + sInvertedPlusOne;
						return sInvertedPlusOne;
					}
				}
			}
		}
		return sInvertedPlusOne;
		
	}

	
	/**
	 * Get the decimal value of a hex digit
	 * @param hexDigit. A hex digit: [0-9A-F]
	 * @return decimal value of the hex digit
	 */
	private static int getDecValueOfHexDigit(char hexDigit) {
		//String finalHexVal = "";
		if ( (hexDigit >= '0') && (hexDigit <= '9') ) {
			return Integer.parseInt(hexDigit +"");
		}
		//else if ( (hexDigit >= 'A') && (hexDigit <= 'F') ){
		else {
			return ( (int)(hexDigit) - (int)('A') + 10 );
		}
		// return 0;
	}

	/**
	 * Get the binary value of a hex digit
	 * @param hexDigit. A hex digit: [0-9A-F].
	 * @return binary value of hex digit.
	 */
	private static String getBinaryValuesOfHexDigit(char hexDigit) {
		String finalResult = "";
		int decVal = 0;
		if ( (hexDigit >= '0') && (hexDigit <= '9') ) {
			decVal = Integer.parseInt(hexDigit +"");
		}
		else {
			decVal = ( (int)(hexDigit) - (int)('A') + 10 );
		}
		
		while (decVal > 0) {
			finalResult = (decVal % 2) + finalResult;
			decVal = decVal / 2;
		}
		int l = finalResult.length();
		if (l < 4) {
			for (int i = 0; i < (4-l); i++) {
				finalResult ="0"+finalResult;
			}
		}
		return finalResult;
	}
}
