/**
 * Binary class
 * @author Kevin
 *
 */

public class Binary {

	private String binVal;
	private String decVal;
	private String hexVal;
	
	/**
	 * Default constructor
	 */
	public Binary() {
		this("", "", "");
	}
	
	/**
	 * Custom constructor
	 * @param binVal binary value
	 * @param decVal decimal value
	 * @param hexVal hexadecimal value
	 */
	public Binary(String binVal, String decVal, String hexVal) {
		this.binVal = binVal;
		this.decVal = decVal;
		this.hexVal = hexVal;
	}

	/**
	 * Custom constructor
	 * @param binVal binary value
	 */
	public Binary(String binVal) {
		this.binVal = binVal;
		this.decVal = "";
		this.hexVal = "";
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
	 * @param decVal dec value
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
	 * Convert bin to decimal. Set decVal.
	 */
	public void convertToDecimal() {
		if (binVal != null) {
			String binVal_temp = new String(binVal);
			String numberSign = "";
			if (Menu.isNegative(binVal)) {
				binVal_temp = binVal.substring(1);
				numberSign = "-";
			}
			int digits = binVal_temp.length();
			int finalVal = 0;
			for (int i = 0; i < digits; i++) {
				int intVal = Integer.parseInt(binVal_temp.charAt(i)+"");
				finalVal += intVal * Math.pow(2, digits-i-1);
			}
			decVal = numberSign + Menu.trimLeadingZeros(finalVal+"");
		}
	}
	
	/**
	 * Convert bin to hexadecimal. Set hexVal
	 */
	public void convertToHexadecimal() {
		//this.convertToDecimal();
		if (binVal != null) {
			String binVal_temp = new String(binVal);
			String numberSign = "";
			if (Menu.isNegative(binVal)) {
				binVal_temp = binVal.substring(1);
				numberSign = "-";
			}
			int digits = binVal_temp.length();
			hexVal = "";
			int i = digits -1;
			int countOfFourDigits = 0;
			while (i>=0) {
				int j = 0;
				int decValOfFourDigits = 0;
				while ((i>=0) && (j < 4)) {
					//System.out.println(i);
					int intVal = Integer.parseInt(binVal_temp.charAt(i)+"");
					decValOfFourDigits += intVal * Math.pow(2, digits-i-1-4*countOfFourDigits);
					i--;
					j++;
				}
				countOfFourDigits += 1;
				//System.out.println(hexVal);
				hexVal = getSingleHexVal(decValOfFourDigits) + hexVal;
			}
			hexVal = numberSign + Menu.trimLeadingZeros(hexVal);
		}
	}
	
	/**
	 * Get the single character for a decimal value less than 16
	 * @param decInt dec integer must be less than 16
	 * @return single character string representing a hex digit
	 */
	private static String getSingleHexVal(int decInt) {
		String finalHexVal = "";
		if (decInt < 16) {
			if (decInt < 10) {
				finalHexVal= decInt + "";
			}
			else {
				finalHexVal = (char)(decInt+(int)('7')) + "";
			}
		}
		return finalHexVal;
	}
		
}
