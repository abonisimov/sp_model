package net.alex.game.model.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class GameEventTest {

    @Test
    void testInit() {
        GameEvent gameEvent = GameEvent.builder().id("1").universeId("1").build();
        long defaultStartTime = gameEvent.getStartTime();
        gameEvent.init();
        assertNotEquals(defaultStartTime, gameEvent.getStartTime());
    }

    @Test
    void testDeserializationGameEvent() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(GameEvent.builder().universeId("1").
                id("123").delay(500).timeUnit(TimeUnit.MILLISECONDS).backupTime(900).build());
        assertTrue(StringUtils.isNotBlank(json));
        GameEvent event = mapper.reader().readValue(json, GameEvent.class);
        assertNotNull(event);
        assertEquals("1", event.getUniverseId());
        assertEquals("123", event.getId());
        assertEquals(500, event.getDelay());
        assertEquals(TimeUnit.MILLISECONDS, event.getTimeUnit());
        assertEquals(900, event.getBackupTime());
        assertEquals(GameEvent.class.getName(), event.getEventClass());
    }

    @Test
    void testChangeDelay() {
        GameEvent gameEvent = GameEvent.builder().id("1").universeId("1").build();
        gameEvent.init();
        long startTime = gameEvent.getStartTime();
        gameEvent.changeDelay(500, TimeUnit.MILLISECONDS);
        assertEquals(startTime + 500, gameEvent.getStartTime());

        startTime = gameEvent.getStartTime();
        gameEvent.changeDelay(1, TimeUnit.SECONDS);
        assertEquals(startTime + 1000, gameEvent.getStartTime());
    }
}