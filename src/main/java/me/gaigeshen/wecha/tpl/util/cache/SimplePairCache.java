package me.gaigeshen.wecha.tpl.util.cache;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 
 * @author gaigeshen
 * @param <K>
 * @param <V>
 */
public class SimplePairCache<K, V> implements PairCacheable<K, V> {
	
	private final Lock lock = new ReentrantLock();
	
	private final int capacity;
	private final Map<K, V> eden;
	private final Map<K, V> longterm;

	public SimplePairCache(int capacity) {
		this.capacity = capacity;
		
		this.eden = new ConcurrentHashMap<>(capacity);
		this.longterm = new WeakHashMap<>(capacity);
	}
	
	@Override
	public V get(K k) {
		
		V v = this.eden.get(k);
		
		if (v == null) {
			
			lock.lock();
			
			// Remove the entry about longterm
			try { v = this.longterm.remove(k); }
			finally { lock.unlock(); }
			
			if (v != null)
				this.eden.put(k, v);
			
		}
		
		return v;
	}

	@Override
	public void put(K k, V v) {
		
		if (this.eden.size() >= this.capacity) {
			
			lock.lock();
			
			try { this.longterm.putAll(this.eden); }
			finally { lock.unlock(); }
			
			this.eden.clear();
			
		}
		
		this.eden.put(k, v);
		
	}

	@Override
	public void remove(K k) {
		
		if (this.get(k) == null)
			return;
		
		if (this.eden.remove(k) == null) {
			
			lock.lock();
			
			try {this.longterm.remove(k);}
			finally { lock.unlock(); }
			
		}
		
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.eden.toString());
		sb.append(this.longterm.toString());
		
		return sb.toString();
		
	}

}
