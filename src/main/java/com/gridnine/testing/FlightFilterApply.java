package com.gridnine.testing;

import java.time.LocalDateTime;

public class FlightFilterApply implements IFlightFilterApply {
    private final String name;
    private final CalcTransfer calcTransfer;
    private final FlightFilter flightFilter;

    public FlightFilterApply(String name, CalcTransfer calcTransfer, FlightFilter flightFilter) {
        this.calcTransfer = calcTransfer;
        this.flightFilter = flightFilter;
        this.name = name;
    }

    @Override
    public boolean hasFit(Flight flight) {
        if (flight == null) {
            throw new NullPointerException("flight is null");
        }

        if (flightFilter == null) {
            throw new NullPointerException("flightFilter is null");
        }

        if (flight.getSegments().size() < 1) {
            throw new IllegalArgumentException("flight segments less 1");
        }

        LocalDateTime minTimeToDeparture = flightFilter.getMinTimeToDeparture();
        return (minTimeToDeparture == null || minTimeToDeparture.compareTo(flight.getSegments().get(0).getDepartureDate()) < 0)
                && (!flightFilter.withoutTransferMistake() || !calcTransfer.hasTransferMistake(flight.getSegments().toArray(new Segment[0])))
                && (flightFilter.getMaxTransferHour() == null || flightFilter.getMaxTransferHour() >= calcTransfer.sumTransferTime(flight.getSegments().toArray(new Segment[0])).toHours());
    }

    public String getName() {
        return name;
    }
}
