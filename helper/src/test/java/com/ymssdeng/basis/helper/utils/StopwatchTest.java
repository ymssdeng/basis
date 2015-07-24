package com.ymssdeng.basis.helper.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.ymssdeng.basis.helper.utils.Stopwatch;

public class StopwatchTest {

    @Test
    public void test() throws InterruptedException {
        Stopwatch stopWatch = new Stopwatch();
        stopWatch.start();

        TimeUnit.SECONDS.sleep(1);
        stopWatch.mark();

        TimeUnit.SECONDS.sleep(1);
        stopWatch.mark();

        List<Long> actuals = stopWatch.getDuration(TimeUnit.SECONDS);
        Assert.assertThat(actuals.get(0), CoreMatchers.is((long) 1));
        Assert.assertThat(actuals.get(1), CoreMatchers.is((long) 2));
    }
}
