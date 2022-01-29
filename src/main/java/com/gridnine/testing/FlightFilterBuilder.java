package com.gridnine.testing;

import java.time.LocalDateTime;

public class FlightFilterBuilder {
    private LocalDateTime maxTimeToDeparture = null;
    private boolean withoutTransferMistake ;
    private Integer maxTransferHour;

    public FlightFilterBuilder setMaxTimeToDeparture(LocalDateTime maxTimeToDeparture){
        this.maxTimeToDeparture = maxTimeToDeparture;
        return this;
    }

    public FlightFilterBuilder withoutTransferMistake(){
        this.withoutTransferMistake = true;
        return this;
    }

    public FlightFilterBuilder setMaxTransferHour(int hour){
        this.maxTransferHour = hour;
        return this;
    }

    public FlightFilter build(){
        FlightFilter res =  new FlightFilter(maxTimeToDeparture, withoutTransferMistake, maxTransferHour);
        maxTimeToDeparture = null;
        withoutTransferMistake = false;
        maxTransferHour = null;
        return res;
    }

    public static FlightFilterBuilder create() {
        return new FlightFilterBuilder();
    }
}
