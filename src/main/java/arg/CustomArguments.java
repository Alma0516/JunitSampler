package arg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.platform.commons.util.Preconditions;

@Getter
@Setter
@AllArgsConstructor
public class CustomArguments implements Arguments {

    private String testCaseKey;


    @Override
    public Object[] get() {
        return new Object[0];
    }

    public static Arguments of(Object... arguments){
        Preconditions.notNull(arguments, "argument array must not be null");
        return () -> arguments;
    }
}
