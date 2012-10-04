package me.arnost.junitrunner;

import org.junit.runner.RunWith;

@RunWith(MyRunner.class)
public class AnotherFullyPassingTest {

    public static final String[] testNames() {
        return new String[]{"one","two","three","four"};
    }
}
