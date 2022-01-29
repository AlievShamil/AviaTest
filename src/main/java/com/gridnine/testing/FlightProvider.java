package com.gridnine.testing;

import java.util.List;

public class FlightProvider implements IFlightProvider {
    private final List<Flight> flightList;

    public FlightProvider(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public Flight[] getFlightToDeparture(IFlightFilterApply flightFilterApply) {
        return flightList.stream().filter(flightFilterApply::hasFit).toArray(Flight[]::new);
    }
}
