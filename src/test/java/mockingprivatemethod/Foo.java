package mockingprivatemethod;

public class Foo {
    public Foo(int x) {}
    public Foo() {}
    public int baz() { return 42; }
    public String bar() { return barPrivate(); }
    private String barPrivate() {
        throw new UnsupportedOperationException();
    }
}
