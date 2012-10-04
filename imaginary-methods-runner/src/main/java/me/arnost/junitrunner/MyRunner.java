package me.arnost.junitrunner;

import org.junit.ComparisonFailure;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyRunner extends Runner {

    private final String[] names;
    private final Class testClass;

    public MyRunner(Class testClass) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.out.println("Setting-up runner for class" + testClass);
        this.testClass = testClass;
        //names = new String[]{"foo","bar","0har","zar"};
        Method m = testClass.getMethod("testNames");
        String[] result  = (String[])m.invoke(null);
        names = result;
    }

    @Override
    public Description getDescription() {
        return Description.createSuiteDescription(testClass);
    }

    /**
     * Run the tests for this runner.
     *
     * @param notifier will be notified of events while tests are being run--tests being
     *                 started, finishing, and failing
     */
    @Override
    public void run(RunNotifier notifier) {
        for (String test : names) {
            System.out.println("Test " + test);
            
            Description description = Description.createTestDescription(testClass, "test_" + test);

            notifier.fireTestStarted(description);
            if (test.startsWith("0") || test.startsWith("1")) {

                if (test.startsWith("0")) {
                    Failure failure = new Failure(description, new RuntimeException());
                    notifier.fireTestFailure(failure);
                } else {
                    final ComparisonFailure comparisonFailure = new ComparisonFailure("Failed in test " + test, "Valua A", "Value B");
                    StackTraceElement element = new StackTraceElement("xqueryfile1.txt","test_" + test,"xqueryfile1.txt",11);
                    StackTraceElement[] stackTrace = new StackTraceElement[]{element};
                    comparisonFailure.setStackTrace(stackTrace);
                    Failure failure = new Failure(description, comparisonFailure);
                    notifier.fireTestFailure(failure);
                }

            } else {
                notifier.fireTestFinished(description);
            }
        }
    }
}
