package com.example.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentParserTest {

    @Test
    public void testParseDocx() throws IOException {
        String filePath = "src/test/resources/example.docx";
        List<String> keywords = DocumentParser.extractKeywords(filePath, "docx", 1);
        assertTrue(keywords.contains("привет"));
        assertTrue(keywords.contains("мир"));
    }

    @Test
    public void testParseXlsx() throws IOException {
        String filePath = "src/test/resources/example.xlsx";
        List<String> keywords = DocumentParser.extractKeywords(filePath, "xlsx", 1);
        assertTrue(keywords.contains("привет"));
        assertTrue(keywords.contains("мир"));
    }

    @Test
    public void testParsePdf() throws IOException {
        String filePath = "src/test/resources/example.pdf";
        List<String> keywords = DocumentParser.extractKeywords(filePath, "pdf", 1);
        assertTrue(keywords.contains("привет"));
        assertTrue(keywords.contains("мир"));
    }
}
