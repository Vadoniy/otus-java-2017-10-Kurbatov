package Cache;

public interface CacheEngine<K, V> {

    void put(Element<K, V> element);

    V get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();
}