import alma.Tm4jExtension;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SampleParameterizedTest {

    @ParameterizedTest
    @MethodSource("methodSource")
    public void forParameterizedTest(String testData, TestReporter testReporter) {
        testReporter.publishEntry(Tm4jExtension.TEST_CASE_KEY,"SCENARIO_1");
        System.out.println(testData);
    }

    public static Stream<String> methodSource() {
        return Stream.of("arg1","arg2");
    }
}
