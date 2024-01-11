package com.playerdataservice.api.repository;

import com.playerdataservice.api.config.CsvFileLoader;
import com.playerdataservice.api.model.Player;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PlayerRepository {

    private final ConcurrentHashMap<String, Player> players = new ConcurrentHashMap<>();

    private final CsvFileLoader csvFileLoader;

    @Autowired
    public PlayerRepository(CsvFileLoader csvFileLoader) {
        this.csvFileLoader = csvFileLoader;
    }

    @PostConstruct
    private void loadPlayers() {
        csvFileLoader.getPlayers().forEach(player -> players.put(player.getPlayerID(), player));
    }

    public List<Player> findAll() {
        return new ArrayList<>(players.values());
    }

    public Optional<Player> findById(String playerId) {
        return Optional.ofNullable(players.get(playerId));
    }
}
