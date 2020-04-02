import listner.Tm4jExtension;
import annotation.TestCaseKey;
import app.Calculator;
import arg.TestData;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SampleParameterizedTest {

//    @TestCaseKey(keys = "SCENARIO_1")
//    void testName() {
//        assertTrue(true, "work");
//    }


    @ParameterizedTest
    @MethodSource("methodSource")
    @TestCaseKey(keys = {"TEST1", "TEST2"})
    public void oneParameterizedTest(TestData testData) {
        // вариант1
        Calculator calculator = new Calculator();
        int sum = calculator.sum(testData.getArg1(), testData.getArg2());
       assertNotNull(sum,"Not null!");
    }


    @ParameterizedTest
    @MethodSource("methodSource")
    //@TestCaseKey(keys = {"TEST_3", "TEST_4"})
    public void twoParameterizedTest(TestData testData, TestReporter testReporter) {
        Calculator calculator = new Calculator();
        int sum = calculator.sum(testData.getArg1(), testData.getArg2());
        System.out.println(sum);
        //вариант2 - ставить ключи вручгую + убрать аннотацию @Test + убирать TestCaseKey
        Map<String, String> keys = new HashMap<>();
        keys.put(Tm4jExtension.TEST_CASE_KEY.concat("1"), "SCENARIO_1");
        keys.put(Tm4jExtension.TEST_CASE_KEY.concat("2"), "SCENARIO_2");
        testReporter.publishEntry(keys);
    }

    public static Stream<Arguments> methodSource() {
        return Stream.of(Arguments.of(new TestData(1, 1, 2)),
                        Arguments.of(new TestData(2, 2, 4)));
    }
}
