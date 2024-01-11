package com.playerdataservice.api.service;

import com.playerdataservice.api.model.Player;
import com.playerdataservice.api.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    public void testGetAllPlayers() {
        // Arrange
        List<Player> mockPlayers = Arrays.asList(new Player(), new Player());
        given(playerRepository.findAll()).willReturn(mockPlayers);

        // Act
        List<Player> players = playerService.getAllPlayers();

        // Assert
        assertEquals(2, players.size());
    }

    @Test
    public void testGetPlayerByIdWhenExists() {

        String playerID = "1";
        // Arrange
        Player mockPlayer = getPlayer(playerID);
        given(playerRepository.findById(playerID)).willReturn(Optional.of(mockPlayer));

        // Act
        Optional<Player> result = playerService.getPlayerById(playerID);

        // Assert
        assertTrue(result.isPresent(), "Player should be found");
        assertEquals(mockPlayer, result.get(), "Returned player should match the mock player");
    }

    @Test
    public void testGetPlayerByIdWhenDoesNotExist() {
        String playerID = "99";

        // Arrange
        given(playerRepository.findById(playerID)).willReturn(Optional.empty());

        // Act
        Optional<Player> result = playerService.getPlayerById(playerID);

        // Assert
        assertFalse(result.isPresent(), "Player should not be found");
    }

    public static Player getPlayer(String playerID) {
        Player player = new Player();

        player.setPlayerID(playerID);
        player.setBirthYear(1934);
        player.setBirthMonth(2);
        player.setBirthDay(5);
        player.setBirthCountry(" USA");
        player.setBirthState(" AL");
        player.setBirthCity(" Mobile");
        player.setDeathCountry("");
        player.setDeathState("");
        player.setDeathCity("");
        player.setNameFirst(" Hank");
        player.setNameLast(" Aaron");
        player.setNameGiven(" Henry Louis");
        player.setBats(" R");
        player.setMyThrows("R");
        player.setRetroID(" aaroh101");

        return player;
    }

}
