package pl.wildlabs.wildapi.java;

public class Tuple2<A, B> {
    public final A first;
    public final B second;

    public Tuple2(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public Tuple3 tuple3() {
        return new Tuple3<A, B, Void>(first, second, null);
    }
}