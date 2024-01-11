package com.playerdataservice.api.config;

import com.playerdataservice.api.model.Player;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvFileLoader {
    @Value("${playerdata.csv-file-path}")
    private String csvFilePath;

    private List<Player> players = new ArrayList<>();

    @PostConstruct
    public void loadPlayersFromCsv() {

        try (
                InputStreamReader inputStreamReader = new InputStreamReader(new ClassPathResource(csvFilePath).getInputStream(), StandardCharsets.UTF_8);
                CSVParser csvParser = new CSVParser(inputStreamReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        ) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (CSVRecord record : csvParser) {
                Player player = new Player();

                player.setPlayerID(record.get("playerID"));
                player.setBirthYear(parseInteger(record.get("birthYear")));
                player.setBirthMonth(parseInteger(record.get("birthMonth")));
                player.setBirthDay(parseInteger(record.get("birthDay")));
                player.setBirthCountry(record.get("birthCountry"));
                player.setBirthState(record.get("birthState"));
                player.setBirthCity(record.get("birthCity"));
                player.setDeathYear(parseInteger(record.get("deathYear")));
                player.setDeathMonth(parseInteger(record.get("deathMonth")));
                player.setDeathDay(parseInteger(record.get("deathDay")));
                player.setDeathCountry(record.get("deathCountry"));
                player.setDeathState(record.get("deathState"));
                player.setDeathCity(record.get("deathCity"));
                player.setNameFirst(record.get("nameFirst"));
                player.setNameLast(record.get("nameLast"));
                player.setNameGiven(record.get("nameGiven"));
                player.setWeight(parseInteger(record.get("weight")));
                player.setHeight(parseInteger(record.get("height")));
                player.setBats(record.get("bats"));
                player.setMyThrows(record.get("throws"));
                player.setDebut(parseLocalDate(record.get("debut"), dateFormatter));
                player.setFinalGame(parseLocalDate(record.get("finalGame"), dateFormatter));
                player.setRetroID(record.get("retroID"));

                players.add(player);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load players from CSV file", e);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }


    // Helper methods to handle parsing and null cases
    private Integer parseInteger(String value) {
        if (value != null && !value.trim().isEmpty() && !value.equals("\"\"")) {
            return Integer.parseInt(value.trim());
        }
        return 0;
    }

    private LocalDate parseLocalDate(String value, DateTimeFormatter formatter) {
        if (value != null && !value.trim().isEmpty() && !value.equals("\"\"")) {
            return LocalDate.parse(value.trim(), formatter);
        }
        return null;
    }
}
