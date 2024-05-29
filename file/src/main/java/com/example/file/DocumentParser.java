package com.example.file;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DocumentParser {
    public static void main(String[] args) {
        String docxPath = "example.docx";
        String xlsxPath = "example.xlsx";
        String pdfPath = "example.pdf";

        try {
            System.out.println("Parsing DOCX file...");
            parseDocx(docxPath);
            System.out.println("\nParsing XLSX file...");
            parseXlsx(xlsxPath);
            System.out.println("\nParsing PDF file...");
            parsePdf(pdfPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseDocx(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {
            document.getParagraphs().forEach(paragraph -> System.out.println(paragraph.getText()));
        }
    }

    private static void parseXlsx(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            sheet.forEach(row -> {
                row.forEach(cell -> System.out.print(cell.toString() + "\t"));
                System.out.println();
            });
        }
    }

    private static void parsePdf(String filePath) throws IOException {
        try (PDDocument document = PDDocument.load(Files.newInputStream(Paths.get(filePath)))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            System.out.println(text);
        }
    }
}
