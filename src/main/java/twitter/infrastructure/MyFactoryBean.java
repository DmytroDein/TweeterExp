package twitter.infrastructure;

import org.springframework.beans.factory.FactoryBean;


public class MyFactoryBean implements FactoryBean<Temp> {
    @Override
    public Temp getObject() throws Exception {
        return new Temp();
    }

    @Override
    public Class<?> getObjectType() {
        return Temp.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
