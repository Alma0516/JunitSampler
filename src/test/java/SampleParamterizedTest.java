import annotation.TestCaseKey;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleParamterizedTest {

    @TestCaseKey(keys = "key-11")
    void testName() {
        System.out.println("Обычный тест старт");
        assertTrue(true, "Work");
    }

    @ParameterizedTest
    @TestCaseKey(keys = "Parameter-1")
    @MethodSource("methodSource")
    public void forParameterizedTest(String testData) {
        System.out.println("Parameterized test started....");
        System.out.println(testData);
    }

    public static Stream<String> methodSource() {
        return Stream.of("key-p2");
    }
}
