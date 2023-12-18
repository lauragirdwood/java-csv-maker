package com.example;

import com.opencsv.CSVWriter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/")
public class Controller {

    private static final String CSV_FILE_PATH = "output.csv";

    @PostMapping
    public String create(@RequestBody Request request) {
        generateCsv(request);
        return "Hello " + request.getName() + " your age is " + request.getAge();
    }

    private void generateCsv(Request request) {
        // Map que será convertido para CSV
        Map<String, String> dataMap = Map.of(
                "Name", request.getName(),
                "Age", request.getAge()
        );

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
            // Escrever dados do Map para o CSV
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                csvWriter.writeNext(new String[]{entry.getKey(), entry.getValue()});
            }

            System.out.println("Dados adicionados ao CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
