package annotation;

import listner.Tm4jExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD , ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(Tm4jExtension.class)
public @interface TestCaseKey {

    String[] keys();
}
