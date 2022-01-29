package com.gridnine.testing;

import java.time.LocalDateTime;

public class FlightFilter {
    private final LocalDateTime minTimeToDeparture;
    private final boolean withoutTransferMistake;
    private final Integer maxTransferHour;

    public FlightFilter(LocalDateTime minTimeToDeparture, boolean withoutTransferMistake, Integer minTransferHour) {
        this.minTimeToDeparture = minTimeToDeparture;
        this.withoutTransferMistake = withoutTransferMistake;
        this.maxTransferHour = minTransferHour;
    }

    public LocalDateTime getMinTimeToDeparture() {
        return minTimeToDeparture;
    }

    public boolean withoutTransferMistake() {
        return withoutTransferMistake;
    }

    public  Integer getMaxTransferHour() {
        return maxTransferHour;
    }
}
