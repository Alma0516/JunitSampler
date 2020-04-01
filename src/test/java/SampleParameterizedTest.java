import alma.Tm4jExtension;
import annotation.TestCaseKey;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleParameterizedTest {

    @TestCaseKey(keys = "SCENARIO_1")
    void testName() {
        assertTrue(true, "work");
    }


    @ParameterizedTest
    @MethodSource("methodSource")
    public void forParameterizedTest(String testData, TestReporter testReporter) {
        testReporter.publishEntry(Tm4jExtension.TEST_CASE_KEY,"SCENARIO_1");
        System.out.println(testData);// todo передать неск-ко ключей
    }

    public static Stream<String> methodSource() {
        return Stream.of("arg1","arg2");
    }
}
