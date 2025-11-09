package pl.wildlabs.wildapi.java;

public class Tuple3<A, B, C> {
    public final A first;
    public final B second;
    public final C third;

    public Tuple3(A first, B second) {
        this(first, second, null);
    }

    public Tuple3(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Tuple2 tuple2() {
        return new Tuple2<A, B>(first, second);
    }
}
