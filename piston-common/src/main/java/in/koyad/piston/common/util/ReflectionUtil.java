package in.koyad.piston.common.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ReflectionUtil {
	public static List<Field> getAllFields(Class<?> type) {
		List<Field> fields = new LinkedList<>();
	    fields.addAll(Arrays.asList(type.getDeclaredFields()));

	    if (type.getSuperclass() != null) {
	        fields.addAll(getAllFields(type.getSuperclass()));
	    }

	    return fields;
	}
}
