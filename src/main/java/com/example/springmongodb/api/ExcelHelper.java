package com.example.springmongodb.api;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"id", "bookName", "authorName"};
    static String SHEET = "Books";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public  static List<Book> excelToBooks(InputStream is) throws IOException {
        try{
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Book> books = new ArrayList<Book>();
            int rowNumber = 0;
            while (rows.hasNext()){
                Row currentRow = rows.next();
                if (rowNumber==0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Book book = new Book();
                int cellIdx=0;
                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0 -> book.setId((int) currentCell.getNumericCellValue());
                        case 1 -> book.setBookName(currentCell.getStringCellValue());
                        case 3 -> book.setAuthorName(currentCell.getStringCellValue());
                    }
                    cellIdx++;
                }
                books.add(book);
            }
            workbook.close();
            return books;
        } catch (IOException e){
            throw new RuntimeException("fail to parse Excel file: "+ e.getMessage());
        }
    }
}

