package twitter.infrastructure.custom;

import twitter.infrastructure.annotations.Benchmark;
import twitter.infrastructure.annotations.PostConstructAnnotation;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

public class ApplicationContext implements Context {

    private final Config config;
    private final HashMap<String, Object> beanStore = new HashMap<>();

    public ApplicationContext(Config config) {
        this.config = config;
    }

    //Before Services - works!
    /*@Override
    public <T> T getBeanByClass(String beanName) throws Exception {
        *//*T bean = (T)( beanStore.get(beanName));
        if (bean != null) {
            Class<?> clazz = config.getImpl(beanName);
            if (clazz == null) {
                throw new RuntimeException("Bean not found");
            }
            bean = (T) clazz.newInstance();
            beanStore.put(beanName, bean);
        }

       T bean = null;
        if (beanStore.containsKey(beanName)){
            bean = (T)(beanStore.get(beanName));
        } else {
            Class<?> clazz = config.getImpl(beanName);
            if (clazz == null) {
                throw new RuntimeException("Bean not found");
            }
            bean = (T) clazz.newInstance();
            beanStore.put(beanName, bean);
        }*//*

        *//*T bean = null;
        if (beanStore.containsKey(beanName)){
            bean = (T)(beanStore.get(beanName));
        } else {
            Class<?> clazz = config.getImpl(beanName);
            if (clazz == null) {
                throw new RuntimeException("Bean not found");
            }
            bean = (T) clazz.newInstance();
            callInitMethod(bean);
            callAnnotatedBean(bean);

            bean = createProxy(bean);
            beanStore.put(beanName, bean);
        }
        return bean;*//*

        T bean = (T) beanStore.get(beanName);
        if (bean != null) {
            return bean;
        }
        Class<?> clazz = config.getImpl(beanName);
        if (clazz == null) {
            throw new RuntimeException("Bean not found");
        }

        BeanBuilder beanBuilder = new BeanBuilder(clazz);
        beanBuilder.callInitMethod();
        beanBuilder.callAnnotatedBean();
        beanBuilder.createProxy();
        bean = (T) beanBuilder.build();
        return bean;

    }*/

    @Override
    public <T> T getBean(String beanName) throws Exception {

        T bean = (T) beanStore.get(beanName);
        if (bean != null) {
            return bean;
        }
        Class<?> clazz = config.getImpl(beanName);
        if (clazz == null) {
            throw new RuntimeException("Bean not found");
        }

//        Constructor constructor = clazz.getConstructors()[0];
//
//        if (constructor.getParameterCount() == 0){
//            bean = (T) clazz.newInstance();
//            return bean;
//        }
//
//        Class<?>[] constrParTypes = constructor.getParameterTypes();
//
//        Object[] constrParameters = new Object[constrParTypes.length];
//        for (int i = 0; i < constrParTypes.length; i++){
//            String originalClassName = constrParTypes[i].getSimpleName();
//            constrParameters[i] = getBean(unCapitalize(originalClassName));
//        }
//
//        bean = (T)constructor.newInstance(constrParameters);
        //callInitMethod(bean);
        //callAnnotatedBean(bean);
        //bean = createProxy(bean);

//        BeanBuilder beanBuilder = new BeanBuilder(clazz);
        BeanBuilder beanBuilder = new BeanBuilder(beanName);
        beanBuilder.callInitMethod();
        beanBuilder.callAnnotatedBean();
        beanBuilder.createProxy();
        bean = (T) beanBuilder.build();
        beanStore.put(beanName, beanBuilder.getOriginalBean());
        return bean;
    }

    private String unCapitalize(String originalClassName) {
        return Character.toLowerCase(originalClassName.charAt(0)) + originalClassName.substring(1);
    }

    /*private <T> T createProxy(T bean) {

        *//*T newBean = (T)Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    T result = null;
                    String methodName = method.getName();
                    Method[] metods = bean.getClass().getMethods();
                    for (Method m : metods) {
                        if (m.getName().equals(methodName) && m.isAnnotationPresent(Benchmark.class) && m.getAnnotation(Benchmark.class).value()) {
                            System.out.println("Benchmark  annotation found and it is 'true'");
                            long start = System.nanoTime();
                            result = (T) method.invoke(bean, args);
                            long difference = System.nanoTime() - start;
                            System.out.format("Method %s executed during %f seconds.\n", methodName, difference / 1.0E+9);
                            break;
                        }
                    }
                    return result;
                });*//*

        T newBean = (T)Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                (proxy, method, args) -> benchmarkMethod(bean, method, args)
                );
        return newBean;
    }*/

    /*private <T> T benchmarkMethod(T bean, Method method, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
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
    }*/

    /*private void callAnnotatedBean(Object bean) {
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

    private void callInitMethod(Object bean) {
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
    }*/


    public class BeanBuilder<T> {

        private final Class<?> clazz;
        private T bean;
        private T proxyfiedBean;

        public BeanBuilder(String beanName) {
            clazz = config.getImpl(beanName);
            //this.clazz = clazz;

        /*try {
            bean = (T) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }*/

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
                BeanBuilder parameterBeanBuilder = new BeanBuilder(unCapitalize(constrParTypes[i].getSimpleName()));
                parameterBeanBuilder.callInitMethod();
                parameterBeanBuilder.callAnnotatedBean();
                parameterBeanBuilder.createProxy();
                constrParameters[i] = parameterBeanBuilder.build();
            }

            bean = (T)constructor.newInstance(constrParameters);

            callInitMethod();
            callAnnotatedBean();
            createProxy();
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
    }
}
