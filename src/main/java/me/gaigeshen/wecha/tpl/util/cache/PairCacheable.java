package me.gaigeshen.wecha.tpl.util.cache;

/**
 * 
 * 
 * @author gaigeshen
 */
public interface PairCacheable<K, V> {

	V get(K k);
	
	void put(K k, V v);
	
	void remove(K k);
	
}
