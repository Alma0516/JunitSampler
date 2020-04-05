import annotation.TestCaseKey;
import app.Calculator;
import arg.TestData;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

//добавить в  аргументы идентификаторы сценариев
//(сценарий1 -> arg1, arg2, argN)
//(сценарий2 -> arg1, arg2, argN, argN+1)

//Из метода нельзя ефлекчивно получить значения его параметров  - см ссылку
//https://stackoverflow.com/questions/16957032/programmatically-retrive-parameters-and-values-of-a-method
@ExtendWith(TestCaseInstanceFactory.class)
public class SampleParameterizedTest {

    @TestCaseKey(keys = "SCENARIO_1")
    void testName() {
        assertTrue(true, "work");
    }


    @ParameterizedTest
    @TestCaseKey
    //@CustomParamsMarker(testCaseKey = "TC1")
    @MethodSource("methodSource")
    public void oneParameterizedTest(TestData testData) {
        // вариант1
        Calculator calculator = new Calculator();
        int sum = calculator.sum(testData.getArg1(), testData.getArg2());
        assertNotNull(sum, "Not null!");
    }

    public static Stream<Arguments> methodSource() {
        return Stream.of(Arguments.of(new TestData(1, 1, 2, "CASE1==") ),
                Arguments.of(new TestData(2, 2, 4, "CASE2==") ));
    }
}
