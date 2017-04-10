package twitter.infrastructure.custom;


public interface Config {

    Class<?> getImpl(String name);
    //String[] getImplParameters(String name);
}
