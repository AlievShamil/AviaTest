package com.gridnine.testing;

import java.time.Duration;

public class CalcTransfer {

    public Duration sumTransferTime(Segment[] segments) {
        validationSegments(segments);

        Segment currentElem = segments[0];
        Duration sumDuration = Duration.ZERO;
        for (int i = 1; i < segments.length; i++) {
            Duration transferTime = getTransferTime(currentElem, segments[i]);

            sumDuration = sumDuration.plus(transferTime);

            currentElem = segments[i];
        }

        return sumDuration;
    }

    public boolean hasTransferMistake(Segment[] segments) {
        validationSegments(segments);

        Segment currentElem = segments[0];
        if (currentElem.getDepartureDate().compareTo(currentElem.getArrivalDate()) > 0) {
            return true;
        }
        for (int i = 1; i < segments.length; i++) {
            Segment nextElem = segments[i];
            if (nextElem.getDepartureDate().compareTo(nextElem.getArrivalDate()) > 0
                    || currentElem.getArrivalDate().compareTo(nextElem.getDepartureDate()) > 0)
                return true;
            currentElem = nextElem;
        }
        return false;
    }

    private void validationSegments(Segment[] segments) {
        if (segments == null) {
            throw new NullPointerException();
        }
        if (segments.length < 1) {
            throw new IllegalArgumentException();
        }
    }

    private Duration getTransferTime(Segment firstSegment, Segment secondSegment) {
        return Duration.between(firstSegment.getArrivalDate(), secondSegment.getDepartureDate());
    }

}
