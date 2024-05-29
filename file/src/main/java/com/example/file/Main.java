package com.example.file;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String docxFilePath = "src/test/resources/example.docx";
        String xlsxFilePath = "src/test/resources/example.xlsx";
        String pdfFilePath = "src/test/resources/example.pdf";
        int frequencyThreshold = 4;

        try {
            List<String> docxKeywords = DocumentParser.extractKeywords(docxFilePath, "docx", frequencyThreshold);
            System.out.println("DOCX Keywords: " + docxKeywords);

            List<String> xlsxKeywords = DocumentParser.extractKeywords(xlsxFilePath, "xlsx", frequencyThreshold);
            System.out.println("XLSX Keywords: " + xlsxKeywords);

            List<String> pdfKeywords = DocumentParser.extractKeywords(pdfFilePath, "pdf", frequencyThreshold);
            System.out.println("PDF Keywords: " + pdfKeywords);
        } catch (IOException e) {
            System.err.println("Error parsing file: " + e.getMessage());
        }
    }
}
