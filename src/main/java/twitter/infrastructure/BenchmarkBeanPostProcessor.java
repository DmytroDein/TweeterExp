package twitter.infrastructure;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import twitter.infrastructure.annotations.Benchmark;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class BenchmarkBeanPostProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        Object resultBean = bean;
        Method[] methods = bean.getClass().getMethods();
        /*for(Method met: methods){
            if(met.isAnnotationPresent(Benchmark.class) && met.getAnnotation(Benchmark.class).value()){
                System.out.format("Creating benchmark proxy bean for %s\n", beanName);
                return getBenchmarkProxyBean(bean);
            }
        }*/
        boolean isAnnotationFound = Arrays.stream(methods)
                .anyMatch(method -> (method.isAnnotationPresent(Benchmark.class) && method.getAnnotation(Benchmark.class).value()));
        //System.out.println("Bean Name: " + beanName + ":  isAnnotationFound: " + isAnnotationFound);
        if (isAnnotationFound){
            System.out.format("Creating benchmark proxy bean for %s\n", beanName);
            resultBean = getBenchmarkProxyBean(bean);
            //System.out.println("SourceBean: " + bean);
            //System.out.println("ResultBean: " + resultBean);
        }
//        return bean;
        return resultBean;
//        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private Object getBenchmarkProxyBean(Object bean) {
        Object proxifiedBean = Proxy.newProxyInstance(
                bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                (proxy, method, args) -> benchmarkMethod(bean, method, args)
        );
        /*Object proxifiedBean = Proxy.newProxyInstance(
                bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.print(method.getName() + "()  ");
                    long before = System.nanoTime();
                    Object retVal = method.invoke(bean, args);
                    long after = System.nanoTime();
                    System.out.println("method worked: "+(after-before)+" ns.");
                    return retVal;
                }
        );*/
        return proxifiedBean;
    }

    private Object benchmarkMethod(Object bean, Method method, Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Object result;
            String methodName = method.getName();
//            System.out.print(methodName + "(): ");
            Class<?>[] argsTypes = getClassesArray(args);
            Method checkedMethod = bean.getClass().getMethod(methodName, argsTypes);

            if (checkedMethod.isAnnotationPresent(Benchmark.class) && checkedMethod.getAnnotation(Benchmark.class).value()) {
                System.out.print(methodName + "(): ");
                System.out.println("'Benchmark' annotation found and it is 'true'");
                long start = System.nanoTime();
                result = method.invoke(bean, args);
                long difference = System.nanoTime() - start;
                System.out.format("Method %s() executed in %f seconds.\n", methodName, difference / 1.0E+9);
            } else {
                //System.out.println("Invocation without annotations.");
                result = method.invoke(bean, args);
            }
            return result;
    }

    private Class<?>[] getClassesArray(Object[] args) {
        Class<?>[] argsTypes = null;
        if (args != null) {
            argsTypes = Arrays.stream(args)
                    .map(e -> e.getClass())
                    .toArray(e -> new Class<?>[args.length]);
        }
        return argsTypes;
    }
}
