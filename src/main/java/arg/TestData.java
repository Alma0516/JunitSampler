package arg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//Сделать возможность задавать переменное число аргументов
public class TestData {

    private int arg1;

    private int arg2;

    private int result;

    private String testCaseKey;

    @Override
    public String toString() {
        return "TestData{" +
                "arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", result=" + result +
                ", testCaseKey='" + testCaseKey + '\'' +
                '}';
    }
}
