package helper;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import logging.LoggingHandling;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pojos.pojoRequests.Accounts.SignIn;

import java.io.*;
import java.util.*;

import static logging.LoggingHandling.logger;

/**
 * this class for generic action which is needed in script like:
 * 1-take screenshot when the test cases fail
 * 2-choose test data file with specific format
 * 3-retrieve data from those test data and send them to data provider which in turn send them to test method
 */

public class Helper {
    private static int DataRow ;
	private static int DataCol ;
	private static Object[][] data;

	private  Map<String,Object> ResponseContext = new HashMap();;
	private Response response;

	public Response getResponse() {
		return this.response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public  Response getLatestResponse() {
		return (Response)this.ResponseContext.get("LATEST_RESPONSE");
	}

	public  void setLatestResponse(Response response) {
		this.ResponseContext.put("LATEST_RESPONSE", response);
	}
	/**
	 *convert DataSizeRow from String to Integer then assign int value to DataRow
	 * @param DataSizeRow to set DataSizeRow
	 */
	public static void setRow(String DataSizeRow)
	{
		Integer Row =Integer.valueOf(DataSizeRow);
		DataRow=Row.intValue();
	}

	/**
	 *convert DataSizeCol from String to Integer then assign int value to DataCol
	 * @param DataSizeCol to set DataSizeCol
	 */
	public static void setCol(String DataSizeCol)
	{
		Integer Col =Integer.valueOf(DataSizeCol);
		DataCol=Col.intValue();
	}



	/**
	 * this method responsible for:choosing spesific file with specific format
	 * @param fileExtension to pass fileExtension from dataProvider method which came from testngSuite.xml
	 * @param fileName to pass fileName from dataProvider method which came from testngSuite.xml
	 * @return 2d array from type object to receive the data in data provider
	 */
	public static Object[][] chooseFile(String fileExtension ,String fileName)  {
		data = new Object[DataRow][DataCol];
		if (fileExtension.equalsIgnoreCase(".properties") && fileName.equalsIgnoreCase(fileName)) {
			String filePath = "Configs/Data/"+fileName + fileExtension;
			try {
			data = readFromPropertyFile(filePath);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("Error");
				LoggingHandling.logError(e);
				System.out.println("Please Check your file name or file extension ");
			}
		} else if (fileExtension.equalsIgnoreCase(".xlsx") && fileName.equalsIgnoreCase(fileName)) {
			String filePath = "Configs/Data/"+fileName + fileExtension;
			try {
				data = readFromExcelFile(filePath);
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("Error");
				LoggingHandling.logError(e);
				System.out.println("Please Check your file name or file extension ");
			}
		} else if (fileExtension.equalsIgnoreCase(".csv") && fileName.equalsIgnoreCase(fileName)) {
		String filePath = "Configs/Data/"+fileName + fileExtension;
			try {
				data = readFromCSVFile(filePath);
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("Error");
				LoggingHandling.logError(e);
				System.out.println("Please Check your file name or file extension ");
			}
		}

		return data;
	}

	/**
	 *this method responsible for: read data from Property file
	 * @param filepath to pass filepath from chooseFile method
	 * @return 2d array from type object to receive the data in data provider
	 */
	public static Object[][] readFromPropertyFile(String filepath) {

		File file = new File(filepath);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e1) {
			System.out.println("file not found");
			e1.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(reader);
		} catch (IOException e) {
			System.out.println("file not read");
			e.printStackTrace();
		}

		Enumeration names = prop.propertyNames();

		ArrayList<String> listOfNames = new ArrayList<>();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			listOfNames.add(name);
			System.out.println(name);

		}

		for (int i = 0; i < data.length; i++) {

			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = prop.getProperty(listOfNames.get(j));
			}
		}

		return data;
	}

	/**
	 *this method responsible for: read data from Excel file
	 * @param filePath to pass filepath from chooseFile method
	 * @return 2d array from type object to receive the data in data provider
	 * @throws IOException if file is not found
	 */
	public static Object[][] readFromExcelFile(String filePath) throws IOException {

		File srcFile = new File(filePath);


		FileInputStream fis = new FileInputStream(srcFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int TotalNumberOfRows = (sheet.getLastRowNum()+1);
		int TotalNumberOfCols = data[0].length;

		String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfCols];
		ArrayList<String> listOfValues = new ArrayList<>();
		for (int i = 0; i < TotalNumberOfRows; i++) {

			for (int j = 0; j < TotalNumberOfCols; j++) {
				XSSFRow row = sheet.getRow(i);
				String value="";
				if(row.getCell(j).getCellTypeEnum()== CellType.STRING)
				{ value= row.getCell(j).getStringCellValue();}
				else if(row.getCell(j).getCellTypeEnum()==CellType.NUMERIC)
				{value = String.valueOf((int)row.getCell(j).getNumericCellValue());}



					listOfValues.add(value);
					arrayExcelData[i][j] = listOfValues.get(j);

				System.out.println(listOfValues.get(j));
			}
		}

		wb.close();
		return arrayExcelData;
	}

	/**
	 * this method responsible for: read data from CSV file
	 * @param filepath to pass filepath from chooseFile method
	 * @return 2d array from type object to receive the data in data provider
	 * @throws IOException if file is not found
	 */
	public static Object[][] readFromCSVFile(String filepath) throws IOException {
		String csvCell;
		String[] values = new String[0];
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		while ((csvCell = br.readLine()) != null)   //returns a Boolean value
		{
			 values = csvCell.split(",");// use comma as separator

		}

		for (int i = 0; i < data.length; i++) {

			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = values[j];
				System.out.println(data[i][j].toString());
			}
		}

		return data;
	}

	public static ResponseSpecBuilder performValidations(ResponseSpecBuilder responseSpecBuilder, List<List<String>> rows, boolean bodyValidationType) {
		Iterator var4 = rows.iterator();

		while(var4.hasNext()) {
			List<String> row = (List)var4.next();
			String firstValue = (String)row.get(0);
			String secondValue = (String)row.get(1);
			String expectedValue = getExpectedValue(row);
			if (bodyValidationType) {
				responseSpecBuilder.expectBody(firstValue, MatchersConverter.getMatcher(secondValue, expectedValue));
			} else {
				responseSpecBuilder.expectHeader(firstValue, MatchersConverter.getMatcher(secondValue, expectedValue));
			}
		}

		return responseSpecBuilder;
	}

	private static String getExpectedValue(List<String> row) {
		String expectedValue = new String();
		if (row.get(2) == null) {
			expectedValue = "";
		} else if (((String)row.get(2)).contains("{") && ((String)row.get(2)).contains("}")) {
			if (((String)row.get(2)).contains("stored{")) {
				expectedValue = replaceStored((String)row.get(2));
			}
		} else {
			expectedValue = (String)row.get(2);
		}

		return expectedValue;
	}
	public static String replaceStored(String str) {
		String result = str;
		Map<String, Object> context = new HashMap();

		String key;
		String value;
		for(String storedStr = "stored{"; result.contains(storedStr); result = result.replace(storedStr + key + "}", value)) {
			String substring = result.substring(result.indexOf(storedStr));
			key = substring.substring(storedStr.length(), substring.indexOf(125));
			value = String.valueOf(context.get(key));
		}

		return result;
	}

	public SignIn getRequestParams(Map<String, String> paramsMap) {
		Map<String, String> defaultMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		defaultMap.put("", "");

		//overwrite with values from params map
		defaultMap.putAll(paramsMap);
		defaultMap.entrySet().removeIf(values -> values.getValue() == null || "null".equalsIgnoreCase(values.getValue()) || values.getValue().isEmpty());

		return mapper.convertValue(defaultMap, SignIn.class); //sign in is example
	}

}
