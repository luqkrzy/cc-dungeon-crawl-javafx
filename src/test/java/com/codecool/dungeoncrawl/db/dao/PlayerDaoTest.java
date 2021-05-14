package com.codecool.dungeoncrawl.db.dao;

import com.codecool.dungeoncrawl.model.ActorModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PlayerDaoTest {

    @Mock
    PlayerDao playerDao;

    @Test
    void get_whenGivenId_ShouldReturnPlayer() {
        // given
        ActorModel player = new ActorModel("luq", "Player", 2, 5, 4, 6, 56);
        player.setId(2);
        given(playerDao.get(2)).willReturn(player);
        // when
        ActorModel getplayer = playerDao.get(2);
        // then
        assertEquals("luq", getplayer.getActorName());
        assertEquals(2, getplayer.getX());
        assertEquals(4, getplayer.getDefense());
    }

    @Test
    void get_whenGivenWrongId_ShouldReturnNull() {
        // given
        ActorModel mockPlayer = new ActorModel("luq", "Player", 2, 5, 4, 6, 56);
        mockPlayer.setId(2);
        // when
        ActorModel player = playerDao.get(105);
        // then
        assertNull(player);
    }

}
