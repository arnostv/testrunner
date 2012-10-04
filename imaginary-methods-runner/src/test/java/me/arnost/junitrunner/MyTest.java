package me.arnost.junitrunner;

import org.junit.runner.RunWith;

@RunWith(MyRunner.class)
public class MyTest {

    public static final String[] testNames() {
        return new String[]{"foo","bar","1har","zar","1assert","0runtime","lastOne"};
    }
}
