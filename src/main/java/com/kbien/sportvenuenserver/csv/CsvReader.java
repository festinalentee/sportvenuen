package com.kbien.sportvenuenserver.csv;

import com.kbien.sportvenuenserver.entity.OpeningDetails;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.util.List;

@Component
public class CsvReader {

    public List<OpeningDetails> readCsvFormat(String text) {
        StringReader reader = new StringReader(text);
        CsvToBean<OpeningDetails> csvToBean = new CsvToBeanBuilder<OpeningDetails>(reader)
                .withType(OpeningDetails.class)
                .withSeparator(';')
                .build();

        return csvToBean.parse();
    }
}

