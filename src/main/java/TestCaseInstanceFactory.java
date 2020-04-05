import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstanceFactory;
import org.junit.jupiter.api.extension.TestInstanceFactoryContext;
import org.junit.jupiter.api.extension.TestInstantiationException;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

public class TestCaseInstanceFactory implements TestInstanceFactory {
    @Override
    public Object createTestInstance(TestInstanceFactoryContext factoryContext, ExtensionContext extensionContext) throws TestInstantiationException {
        Optional<Object> outerInstance = factoryContext.getOuterInstance();
        if (outerInstance.isPresent()) {
            Object testInstance = outerInstance.get();
            return Enhancer.create(testInstance.getClass(), (MethodInterceptor) (o, method, objects, methodProxy) -> {
                System.out.println(Arrays.toString(objects));
                return method.invoke(testInstance, objects);
            });
        } else {
            try {
                Class<?> testClass = factoryContext.getTestClass();
                Object testInstance = testClass.getConstructors()[0].newInstance();
                return Enhancer.create(testClass, (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    System.out.println(Arrays.toString(objects));
                    return method.invoke(testInstance, objects);
                });
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
