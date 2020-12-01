package com.example.springbootmap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileParser {

    private static final String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public List<Point> getData() throws IOException {

        List<Point> points = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String values = restTemplate.getForObject(url, String.class);

        StringReader stringReader = new StringReader(values);
        CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

        for (CSVRecord csvRecord : csvParser) {
            double lat = 0;
            double lon = 0;
            if (csvRecord.get("Lat").length() != 0) {
                lat = Double.parseDouble(csvRecord.get("Lat"));
            }

            if (csvRecord.get("Long").length() != 0) {
                lon = Double.parseDouble(csvRecord.get("Long"));
            }

            String text = csvRecord.get("11/30/20");
            points.add(new Point(lat, lon, text));
            System.out.println(lat + " " + lon + " " + text);
        }

        return points;
    }
}
