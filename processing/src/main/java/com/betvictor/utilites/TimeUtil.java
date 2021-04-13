package com.betvictor.utilites;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class TimeUtil {

    private StopWatch stopWatch;

    public void startStopWatch() {
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    public double getElapsedTimeInSeconds() {
        stopWatch.stop();
        return stopWatch.getTotalTimeSeconds();
    }
}
