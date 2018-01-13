import mockit.Expectations;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class PrivateMethodMockingTest {

    public static class Foo {
        private void bar() {
            throw new RuntimeException();
        }
    }

    @Test
    public void partialMockingInstance() {

        //
        // this feature was deprecated on v1.23
        // See Rogerio's comments (Aug 11 '16 at 17:23) at:
        //  https://stackoverflow.com/a/35055535/614800
        //

        Foo foo = new Foo();

        new Expectations(foo) {{
            foo.bar();
        }};

        foo.bar();
    }

}
