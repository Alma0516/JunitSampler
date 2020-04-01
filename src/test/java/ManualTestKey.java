import alma.Tm4jExtension;
import org.junit.Assert;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ManualTestKey {

    @ParameterizedTest
    @MethodSource("methodSource")
    public void forParameterizedTest(String testCaseKey, TestReporter testReporter) {
        testReporter.publishEntry(Tm4jExtension.TEST_CASE_KEY,testCaseKey);
    }

    public static Stream<String> methodSource() {
        return Stream.of( "TEST_PROJECT_PARAM");
    }
}
