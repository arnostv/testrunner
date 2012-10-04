package me.arnost.junitrunner;

import org.junit.runner.RunWith;

@RunWith(MyRunner.class)
public class CoupleOfFaliruresTest {

    public static final String[] testNames() {
        return new String[]{"good","fine","1fails"};
    }
}
