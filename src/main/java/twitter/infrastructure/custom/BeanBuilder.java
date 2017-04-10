package twitter.infrastructure.custom;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Optional;

/*public class BeanBuilder<T> {

    private final Class<?> clazz;
    private T bean;
    private T proxyfiedBean;

    public BeanBuilder(Class<?> clazz) {
        this.clazz = clazz;

        *//*try {
            bean = (T) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }*//*

        try {
            bean = getBeanByClass(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T getBeanByClass(Class<?> clazz) throws Exception {

        Constructor constructor = clazz.getConstructors()[0];

        if (constructor.getParameterCount() == 0){
            bean = (T) clazz.newInstance();
            return bean;
        }

        Class<?>[] constrParTypes = constructor.getParameterTypes();

        Object[] constrParameters = new Object[constrParTypes.length];
        for (int i = 0; i < constrParTypes.length; i++){
            //String originalClassName = constrParTypes[i].getSimpleName();
//            constrParameters[i] = getBeanByClass(constrParTypes[i]);
            BeanBuilder parameterBeanBuilder = new BeanBuilder(constrParTypes[i]);
            //parameterBeanBuilder.callInitMethod();
            //parameterBeanBuilder.callAnnotatedBean();
            //parameterBeanBuilder.createProxy();
            constrParameters[i] = parameterBeanBuilder.build();
        }

        bean = (T)constructor.newInstance(constrParameters);


        //callInitMethod();
        //callAnnotatedBean();
        //createProxy();
        return bean;
    }

    private String unCapitalize(String originalClassName) {
        return Character.toLowerCase(originalClassName.charAt(0)) + originalClassName.substring(1);
    }


    public void callInitMethod() {
        Class<?> clazz = bean.getClass();
        Optional<Method> initMethod = Arrays.stream(clazz.getMethods())
                .filter(m -> "init".equals(m.getName()))
                .findFirst();
        initMethod.ifPresent(m -> {
            try {
                m.invoke(bean);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public void callAnnotatedBean() {
        Class<?> clazz = bean.getClass();
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(PostConstructAnnotation.class)) {
                try {
                    m.invoke(bean);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createProxy() {
        proxyfiedBean = (T) Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                (proxy, method, args) -> benchmarkMethod(bean, method, args)
        );
    }


    public T build() {
        if (proxyfiedBean != null) {
            return proxyfiedBean;
        } else {
            return bean;
        }
    }

    private <T> T benchmarkMethod(T bean, Method method, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        T result;
        String methodName = method.getName();
        System.out.println(methodName + "()");
        Class<?>[] argsTypes = getClassesArray(args);
        Method checkedMethod = bean.getClass().getMethod(methodName, argsTypes);

        if (checkedMethod.isAnnotationPresent(Benchmark.class) && checkedMethod.getAnnotation(Benchmark.class).value()) {
            System.out.println("'Benchmark' annotation found and it is 'true'");
            long start = System.nanoTime();
            result = (T) method.invoke(bean, args);
            long difference = System.nanoTime() - start;
            System.out.format("Method %s executed during %f seconds.\n", methodName, difference / 1.0E+9);
        } else {
            result = (T) method.invoke(bean, args);
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

    public T getOriginalBean() {
        return bean;
    }
}*/
