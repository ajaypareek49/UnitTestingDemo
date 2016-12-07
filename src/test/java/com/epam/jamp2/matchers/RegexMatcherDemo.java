package com.epam.jamp2.matchers;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alexey on 07.12.2016.
 */
public class RegexMatcherDemo {
    @Test
    public void shouldMatch() {
        String s ="aaabbbaaaa";
        assertThat(s, RegexMatcher.matchesRegex("a*b*a*"));
    }

    @Test
    public void shoudNotMatch() {
        String s ="aaabbbaaaa";
        assertThat(s, RegexMatcher.matchesRegex("qwr"));
    }
}
