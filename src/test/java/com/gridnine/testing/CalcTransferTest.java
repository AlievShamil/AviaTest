package com.gridnine.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcTransferTest {
    CalcTransfer calcTransfer;

    @Before
    public void setUp() throws Exception {
        calcTransfer = new CalcTransfer();
    }

    @Test
    public void shouldTransferZeroTimeWhenOneSegment() {
        Segment firstSegment = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        assertThat(calcTransfer.sumTransferTime(new Segment[]{firstSegment})).isZero();
    }


    @Test
    public void shouldSegmentMistakeWhenTransferNegativeTime() {
        Segment firstSegment = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment secondMistakeSegment = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(5));

        assertThat(calcTransfer.hasTransferMistake(new Segment[]{firstSegment, secondMistakeSegment})).isTrue();

        Segment secondSegment = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4));

        assertThat(calcTransfer.hasTransferMistake(new Segment[]{firstSegment, secondSegment, secondMistakeSegment})).isTrue();
    }

    @Test
    public void shouldSegmentNotMistakeWhenTransferPositiveTime() {
        Segment firstSegment = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment secondSegment = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(5));

        assertThat(calcTransfer.hasTransferMistake(new Segment[]{firstSegment, secondSegment})).isFalse();

        Segment thirdSegment = new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(10));

        assertThat(calcTransfer.hasTransferMistake(new Segment[]{firstSegment, secondSegment, thirdSegment})).isFalse();
    }

    @Test
    public void shouldSumTransferTimeCorrect() {
        Segment firstSegment = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment secondSegment = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(5));

        assertThat(calcTransfer.sumTransferTime(new Segment[]{firstSegment, secondSegment})).hasHours(1);

        Segment thirdSegment = new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(10));

        assertThat(calcTransfer.sumTransferTime(new Segment[]{firstSegment, secondSegment, thirdSegment})).hasHours(2);

        Segment fourSegment = new Segment(LocalDateTime.now().plusHours(12), LocalDateTime.now().plusHours(13));

        assertThat(calcTransfer.sumTransferTime(new Segment[]{firstSegment, secondSegment, thirdSegment, fourSegment})).hasHours(4);
    }

    @After
    public void tearDown() throws Exception {

    }
}
