package inno.lab5.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;

@UtilityClass

public class BeanUtils {
    @SneakyThrows
    public void copyNonNullProperties(Object source, Object destination){
        Class<?> cl = source.getClass();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            Object value = field.get(source);
            if(value != null){
                field.set(destination,value);
            }
        }
    }
}
