package net.alex.game.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Getter
@SuperBuilder
@Jacksonized
public class ColonyDescription {
    private final String id;
    private final String universeId;
    private final String name;
}
