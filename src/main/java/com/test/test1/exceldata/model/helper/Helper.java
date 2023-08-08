package com.test.test1.exceldata.model.helper;

import com.test.test1.exceldata.model.UserExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

    public static boolean isExcel(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            return true;
        return false;
    }

    public static List<UserExcel> convert(InputStream is) {
        List<UserExcel> list = new ArrayList<>();
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("data");
            Iterator<Row> iterator = sheet.iterator();
            int rowNumber = 0;
            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.cellIterator();
                int cid = 0;
                UserExcel user = new UserExcel();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            double value = cell.getNumericCellValue();
                            user.setId((long) value);
                            break;
                        case 1:
                            user.setFirstname(cell.getStringCellValue());
                            break;
                        case 2:
                            user.setLastname(cell.getStringCellValue());
                            break;
                        case 3:
                            user.setEmail(cell.getStringCellValue());
                            break;
                        case 4:
                            user.setProfession(cell.getStringCellValue());
                            break;
                        default:break;
                    }
                    cid++;
                }
                list.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
