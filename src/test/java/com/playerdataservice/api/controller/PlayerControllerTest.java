package com.playerdataservice.api.controller;

import com.playerdataservice.api.model.Player;
import com.playerdataservice.api.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    @Test
    public void testGetAllPlayers() throws Exception {
        Player player1 = new Player();
        player1.setPlayerID("1");
        Player player2 = new Player();
        player2.setPlayerID("2");

        given(playerService.getAllPlayers()).willReturn(Arrays.asList(player1, player2));

        mockMvc.perform(get("/api/players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].playerID").value("1"))
                .andExpect(jsonPath("$[1].playerID").value("2"));
    }

    @Test
    public void testGetPlayerById() throws Exception {
        Player player = new Player();
        player.setPlayerID("1");

        given(playerService.getPlayerById("1")).willReturn(Optional.of(player));

        mockMvc.perform(get("/api/players/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerID").value("1"));
    }
}
