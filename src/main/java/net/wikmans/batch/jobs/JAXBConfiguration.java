package net.wikmans.batch.jobs;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dawi on 2015-12-11.
 */
@ApplicationPath("/")
public class JAXBConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(BatchResource.class);
        classes.add(ObjectMapperProvider.class);
        classes.add(JacksonFeature.class);
        classes.add(JacksonFilterProvider.class);
        return classes;
    }
}
