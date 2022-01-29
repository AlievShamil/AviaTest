package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        print(flights.toArray(new Flight[0]));

        FlightProvider flightProvider = new FlightProvider(flights);
        CalcTransfer calcTransfer = new CalcTransfer();
        List<IFlightFilterApply> listIFilterApply = new ArrayList<>();
        FlightFilterBuilder flightFilterBuilder = FlightFilterBuilder.create();
        FlightFilter flightFilterAfterNotTime =
                flightFilterBuilder
                        .setMaxTimeToDeparture(LocalDateTime.now())
                        .build();
        listIFilterApply.add(new FlightFilterApply("Исключить полеты с вылетом до текущего момента времени", calcTransfer, flightFilterAfterNotTime));

        FlightFilter flightFilterWithoutTransferMistake =
                flightFilterBuilder
                        .withoutTransferMistake()
                        .build();
        listIFilterApply.add(new FlightFilterApply("Исключить полеты, имеющие сегменты с датой прилёта раньше даты вылета", calcTransfer, flightFilterWithoutTransferMistake));

        FlightFilter flightFilterSumTransferTime =
                flightFilterBuilder
                        .setMaxTransferHour(2)
                        .build();
        listIFilterApply.add(new FlightFilterApply("Исключить полеты, у которых общее время проведённое на земле превышает два часа ",calcTransfer, flightFilterSumTransferTime));

        for (IFlightFilterApply flightFilterApply : listIFilterApply) {
            Flight[] flightToDeparture = flightProvider.getFlightToDeparture(flightFilterApply);
            System.out.println(((FlightFilterApply)flightFilterApply).getName());
            print(flightToDeparture);
        }
    }

    private static void print(Flight[] flightToDeparture) {
        for (Flight f : flightToDeparture) {
            System.out.println(f);
        }
        System.out.println();
    }


}
