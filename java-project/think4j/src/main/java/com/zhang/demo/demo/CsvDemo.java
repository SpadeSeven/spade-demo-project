package com.zhang.demo.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvDemo {

  public static void main(String[] args) {
    FileWriter fileWriter;
    CSVPrinter csvPrinter = null;
    CSVFormat csvFormat = CSVFormat.EXCEL;
    CSVFormat.DEFAULT.withQuote(' ').withEscape(' ');
    try {
      fileWriter = new FileWriter("C:\\Users\\93908\\Desktop\\jd\\产品维度_1.csv");
      csvPrinter = new CSVPrinter(fileWriter, csvFormat);
      Files.readAllLines(Paths.get("C:\\Users\\93908\\Desktop\\jd\\产品维度.csv"));
      for (String line : Files.readAllLines(Paths.get("C:\\Users\\93908\\Desktop\\jd\\产品维度.csv"))) {
        csvPrinter.printRecord(Arrays.asList(line.split(",")));
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (csvPrinter != null) {
          csvPrinter.flush();
          csvPrinter.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
