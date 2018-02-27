package Cache;

import com.sun.istack.internal.NotNull;

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.function.Function;

public class CacheEngineImpl<K, V> implements CacheEngine {
    private static final int TIME_THRESHOLD_MS = 5;

    private final int maxElement;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal; //should we carry about time or not

    private final Map<K, SoftReference<Element<K, V>>> elements = new HashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    public CacheEngineImpl(int maxElement, long lifeTimeMs, long idleTimeMs, boolean isEternal) {
        this.maxElement = maxElement;
        this.lifeTimeMs = lifeTimeMs > 0 ? lifeTimeMs : 0;
        this.idleTimeMs = idleTimeMs > 0 ? lifeTimeMs : 0;
        this.isEternal = (idleTimeMs > 0 || idleTimeMs > 0) || isEternal; //should we carry about time or not
    }


    public void put(Element element) {

        if (elements.size() >= maxElement){
            K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
            String info = "Elements key %s was removed from cache.";
            System.out.println(String.format(info, firstKey));
        }

        K key = (K) element.getKey();
        elements.put(key, new SoftReference<>(element));

        if (!isEternal){
            if (lifeTimeMs != 0){
                TimerTask lifeTimerTask = getTimerTask(key, lifeElement -> lifeElement.getCreationTime() + lifeTimeMs);
                timer.schedule(lifeTimerTask, lifeTimeMs);
            }
            if (idleTimeMs != 0){
                TimerTask idleTimerTask = getTimerTask(key, idleElement -> idleElement.getLastAccessTime() + idleTimeMs);
                timer.schedule(idleTimerTask, idleTimeMs);
            }
        }
    }

    @Override
    public V get(Object key) {
        SoftReference<Element<K, V>> softReference = elements.get(key);

        if (!Objects.isNull(softReference)){
            Element<K, V> softElement = softReference.get();

            if (!Objects.isNull(softElement)){
                hit++;
                softElement.setAccessed();
                return (V) softElement.getValue();
            }
        }

        miss++;
        return null;
    }

    public int getHitCount() {
        return hit;
    }

    public int getMissCount() {
        return miss;
    }

    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final K key, Function<Element<K, V>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                Element<K, V> element = elements.get(key).get();
                if (element == null || isT1BeforeT2(timeFunction.apply(element), System.currentTimeMillis())) {
                    elements.remove(key);
                    this.cancel();
                }
            }
        };
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }
}
