package in.koyad.piston.common.basic;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ServiceLoaderUtil {
	
	public static <T> T getInstance(Class<T> cls, ClassLoader loader) {
		ServiceLoader<T> serviceLoader = ServiceLoader.load(cls, loader);
		Iterator<T> iter = serviceLoader.iterator();
		if(iter.hasNext()) {
			return iter.next();
		}
		
		return null;
	}

}
