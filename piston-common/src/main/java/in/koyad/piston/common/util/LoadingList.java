package in.koyad.piston.common.util;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LoadingList<V> {
	
	private List<V> list = null;
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	private Loader<V> loader = null;
	private boolean initialized = false;
	
	public LoadingList(Loader<V> loader) {
		this.loader = loader;
	}
	
	public List<V> values() {
		lock.writeLock().lock();
		
		try {
			if(!initialized) {
				list = loader.load();
				initialized = true;
			}
		} finally {
			lock.writeLock().unlock();
		}
		
		return list;
	}
	
	public void clear() {
		lock.writeLock().lock();
		try {
			list.clear();
			initialized = false;
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public static interface Loader<V> {
		public List<V> load();
	}
	
}
