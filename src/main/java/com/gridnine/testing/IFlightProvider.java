package com.gridnine.testing;

public interface IFlightProvider {
    Flight[] getFlightToDeparture(IFlightFilterApply flightFilterApply);
}
