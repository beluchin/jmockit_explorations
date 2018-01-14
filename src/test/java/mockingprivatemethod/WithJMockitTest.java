package mockingprivatemethod;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class WithJMockitTest {

    public static class Foo {
        private void bar() { throw new RuntimeException(); }
    }

    @Test
    public void partialMockingWithInstance() {

        //
        // this feature was removed on v1.23
        // See:
        //      * http://jmockit.github.io/changes.html > Version 1.23 (Apr 24, 2016)
        //      * Rogerio's comments (Aug 11 '16 at 17:23) at:
        //          https://stackoverflow.com/a/35055535/614800
        //

        Foo foo = new Foo();

        new Expectations(foo) {{
            foo.bar();
        }};

        foo.bar();
    }

    @Test
    public void partialMockingWithoutInstance() {

        //
        // this feature was removed on v1.27. However, it was observed back
        // in version 1.38 - don't know when it was actually reinstated.
        // See Rogerio's comments (Aug 11 '16 at 17:23) at:
        //  https://stackoverflow.com/a/35055535/614800
        //

        new MockUp<Foo>() { @Mock void bar() {} };
        new Foo().bar();
    }
}
