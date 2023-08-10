package com.test.test1.exceldata.model.helper;

import com.test.test1.exceldata.model.UserExcel;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Data
public class DbToExcelHelper {

    public static String[] headers = {"id", "firstname", "lastname", "email", "profession"};
    public static String sheetName = "userData";

    public static ByteArrayInputStream dataToExcel(List<UserExcel> list) throws IOException {

        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {

            Sheet sheet = workbook.createSheet(sheetName);
            Row row = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(headers[i]);
            }
            int rowCount = 1;
            for (UserExcel user : list) {
                Row row1 = sheet.createRow(rowCount++);
                row1.createCell(0).setCellValue(user.getId());
                row1.createCell(1).setCellValue(user.getFirstname());
                row1.createCell(2).setCellValue(user.getLastname());
                row1.createCell(3).setCellValue(user.getEmail());
                row1.createCell(4).setCellValue(user.getProfession());
            }
            workbook.write(out);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
            workbook.close();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
