package com.ymssdeng.basis.helper.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.bj58.seo.core.utils.Randoms;

public class RandomsTest {

    @Test
    public void randTest() {
        List<String> str = new ArrayList<String>();
        str.add("a");
        str.add("b");
        str.add("c");
        str.add("d");
        Collection<String> r = Randoms.rand(str, 2);

        Assert.assertThat(r.size(), CoreMatchers.is(2));
        Assert.assertThat(str.get(0), CoreMatchers.is("a"));
        Assert.assertThat(str.get(1), CoreMatchers.is("b"));
        Assert.assertThat(str.get(2), CoreMatchers.is("c"));
        Assert.assertThat(str.get(3), CoreMatchers.is("d"));
    }
}
