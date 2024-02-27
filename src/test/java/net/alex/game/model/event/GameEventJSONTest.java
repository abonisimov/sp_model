package net.alex.game.model.event;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameEventJSONTest {

    @Test
    void readWriteTest() throws IOException, ClassNotFoundException {
        GameEvent event = GameEvent.builder().universeId("1").id("2").build();
        String json = GameEventJSON.toJSON(event);
        GameEvent result = GameEventJSON.fromJSON(json);

        assertNotNull(result);
    }
}