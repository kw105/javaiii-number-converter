/**
 * Decimal class
 * @author Kevin
 *
 */
public class Decimal {

	private String decVal;
	private String binVal;
	private String hexVal;
	
	/**
	 * Default constructor
	 */
	public Decimal() {
		this("", "", "");
	}
	
	/**
	 * Custom constructor
	 * @param decVal dec val
	 * @param binVal bin val
	 * @param hexVal hex val
	 */
	public Decimal(String decVal, String binVal, String hexVal) {
		this.decVal = decVal;
		this.binVal = binVal;
		this.hexVal = hexVal;
	}
	
	/**
	 * Custom constructor
	 * @param decVal dec val
	 */
	public Decimal(String decVal) {
		this.decVal = decVal;
		this.binVal = "";
		this.hexVal = "";
	}
	
	/**
	 * Getter method for binVal
	 * @return bin val
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
	 * @return dec val
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
	 * @return hexVal
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
	 * Convert decimal to binary. Set binVal.
	 */
	public void convertToBinary() {
		if (decVal != null) {
			String decVal_temp = new String(decVal);
			// String numberSign = "";
			boolean isNegative = false;
			//int numOfBits = 0;
			if (Menu.isNegative(decVal)) {
				decVal_temp = decVal.substring(1);
				isNegative = true;
			}
			
			int intVal = Integer.parseInt(decVal_temp);
			binVal = binaryStringOfInteger(intVal);
			
			if (isNegative) {
				int numOfBits = binVal.length();
				intVal = (int)(Math.pow(2, numOfBits)) - intVal;
				//binVal = "";
				binVal = Menu.trimLeadingZeros(binaryStringOfInteger(intVal));
			}
		}
	}

	/**
	 * Return the binary value represented in a string for a positive integer. Added a 0 in the first digit to show it is positive.
	 * @param i a positive integer
	 * @return binary value represented in a string for the positive integer
	 */
	private static String binaryStringOfInteger (int i) {
		String str1 = new String("");
		while (i > 0) {
			str1 = (i % 2) + str1;
			i = i / 2;
		}
		return ("0"+Menu.trimLeadingZeros(str1));
	}
	
	/**
	 * Convert decimal to hex. Set hexVal.
	 */
	public void convertToHexadecimal() {
		if (decVal != null) {
			String decVal_temp = new String(decVal);
			String numberSign = "";
			if (Menu.isNegative(decVal)) {
				decVal_temp = decVal.substring(1);
				numberSign = "-";
			}

			int intVal = Integer.parseInt(decVal_temp);
			while (intVal > 0) {
				int rem = intVal % 16;
				if (rem < 10 ) {
					hexVal = rem + hexVal;
				}
				else {
					char c1 = (char)(rem + ((int)('A') - 10));
					hexVal = c1 + hexVal;
				}				
				//hexVal = (intVal % 16) + hexVal;
				intVal = intVal / 16;
			}
			hexVal = numberSign + Menu.trimLeadingZeros(hexVal);
		}
	}
		
}
