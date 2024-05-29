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
import java.util.List;

public class DocumentParser {

    public static String parseDocx(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {
            StringBuilder content = new StringBuilder();
            document.getParagraphs().forEach(paragraph -> content.append(paragraph.getText()).append(" "));
            return content.toString().trim();
        }
    }

    public static String parseXlsx(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            StringBuilder content = new StringBuilder();
            sheet.forEach(row -> {
                row.forEach(cell -> content.append(cell.toString()).append(" "));
                content.append("\n");
            });
            return content.toString().trim();
        }
    }

    public static String parsePdf(String filePath) throws IOException {
        try (PDDocument document = PDDocument.load(Files.newInputStream(Paths.get(filePath)))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document).trim();
        }
    }

    public static List<String> extractKeywords(String filePath, String fileType, int frequencyThreshold) throws IOException {
        String content = "";
        switch (fileType) {
            case "docx":
                content = parseDocx(filePath);
                break;
            case "xlsx":
                content = parseXlsx(filePath);
                break;
            case "pdf":
                content = parsePdf(filePath);
                break;
        }
        System.out.println(content);
        return TextProcessor.extractKeywords(content, frequencyThreshold);
    }
}
