public class Pair<K, V> {

    private final K A;
    private final V B;

    /**
     * A pair of two elements.
     * 
     * @param elementA First element.
     * @param elementB Second element.
     */

    public Pair(K elementA, V elementB) {
        this.A = elementA;
        this.B = elementB;
    }

    /**
     * Returns the first element.
     */
    public K getA() {
        return A;
    }

    /**
     * Returns the second element.
     */
    public V getB() {
        return B;
    }

}