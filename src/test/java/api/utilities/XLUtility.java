package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class XLUtility {

    public FileInputStream fileInput;
    public FileOutputStream fileOutput;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public XLUtility(String path){
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fileInput.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fileInput.close();
        return cellCount;

    }


    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell); // return the formatted value of a cell as a String
        } catch (Exception e){
            data = "";
        }
        workbook.close();
        fileInput.close();
        return data;
    }


    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()) // if file not exist, create new file
        {
            workbook = new XSSFWorkbook();
            fileInput = new FileInputStream(path);
            workbook.write(fileOutput);
        }

        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);

        if (workbook.getSheetIndex(sheetName) == -1) // if sheet not exist, create new sheet
            workbook.createSheet(sheetName);
        sheet=workbook.getSheet(sheetName);

        if (sheet.getRow(rowNum) == null) // if row not exist, create new Row
            sheet.createRow(rowNum);
        row = sheet.getRow(rowNum);

        cell = row.createCell(colNum);
        cell.setCellValue(data);
        fileInput = new FileInputStream(path);
        workbook.write(fileOutput);
        workbook.close();
        fileInput.close();
        fileOutput.close();

    }


}
