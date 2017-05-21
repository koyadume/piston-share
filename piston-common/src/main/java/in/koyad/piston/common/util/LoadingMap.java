package in.koyad.piston.common.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import in.koyad.piston.common.basic.exception.FrameworkException;
import lombok.Getter;

public class LoadingMap<K, V> {
	
	private Map<K, Value<V>> map = new ConcurrentHashMap<>();
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	private Loader<K, V> loader = null;
	private boolean reloadEnabled = true;
	
	public LoadingMap(Loader<K, V> loader) {
		this.loader = loader;
	}
	
	public LoadingMap(Loader<K, V> loader, boolean reloadEnabled) {
		this.loader = loader;
	}
	
	public V get(K key) throws FrameworkException {
		lock.readLock().lock();
		
		V value = null;
		try {
			Value<V> newValue = new Value<>();
			Value<V> existingValue = map.putIfAbsent(key, newValue);
			
			//this means key was either absent or mapped to null
			if(null == existingValue) {
				value = loader.load(key);
				if(value == null) {
					map.remove(key);
				} else {
					newValue.setValue(value);
					//notifyAll() method can be invoked inside a synchronized block only
					synchronized (newValue) {
						newValue.notifyAll();
					}
				}
				
			//otherwise key was present
			} else {
				try {
					//while loop is for handling spurious wakeup.
					while(!existingValue.isComputed()) {
						//wait() method can be invoked inside a synchronized block only
						synchronized (existingValue) {
							existingValue.wait();
						}
					}
					value = existingValue.getValue();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			lock.readLock().unlock();
		}
		
		return value;
	}
	
	public void remove(K key) {
		lock.readLock().lock();
		try {
			map.remove(key);
		} finally {
			lock.readLock().unlock();
		}
	}
	
	public void removeAll(K[] keys) {
		lock.readLock().lock();
		try {
			for(K key : keys) {
				map.remove(key);
			}
		} finally {
			lock.readLock().unlock();
		}
	}
	
	public List<V> values() {
		lock.readLock().lock();
		
		List<V> values = null;
		try {
			values = map.values().stream().map(value -> value.getValue()).collect(Collectors.toList());
		} finally {
			lock.readLock().unlock();
		}
		
		return values;
	}
	
	public void reload() throws FrameworkException {
		if(map.isEmpty() || (!map.isEmpty() && reloadEnabled)) { 
			Map<K, V> entries = loader.loadAll();
			lock.writeLock().lock();
			try {
				map.clear();
				for(K key : entries.keySet()) {
					Value<V> value = new Value<>();
					value.setValue(entries.get(key));
					map.put(key, value);
				}
			} finally {
				lock.writeLock().unlock();
			}
		}
	}
	
	public void clear() throws FrameworkException {
		lock.writeLock().lock();
		try {
			map.clear();
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public static interface Loader<K, V> {
		public V load(K key) throws FrameworkException;
		
		public Map<K, V> loadAll() throws FrameworkException;
	}
	
	private static class Value<V> {
		@Getter
		private boolean computed = false;
		
		@Getter
		private V value;
		
		public void setValue(V value) {
			this.value = value;
			computed = true;
		}
	}
	
}
