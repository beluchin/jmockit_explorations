package mockingprivatemethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Foo.class)
public class WithPowerMockTest {

    //
    // It appears to be not possible to use classes nested into
    // the test class. This is due to the need to @PrepareForTest.
    //

    @Test
    public void partiallyMockOneInstance() throws Exception {
        final Foo foo = PowerMockito.spy(new Foo());

        doReturn("hello world").when(foo, method(Foo.class, "barPrivate"))
                .withNoArguments();

        assertThat(foo.bar(), equalTo("hello world"));
        assertThat(foo.baz(), equalTo(42));
    }

    @Test
    public void partiallyMockAllInstances() throws Exception {
        final Foo foo = PowerMockito.spy(new Foo());

        doReturn("hello world").when(foo, method(Foo.class, "barPrivate"))
                .withNoArguments();
        whenNew(Foo.class).withAnyArguments().thenReturn(foo);

        final Foo anotherFoo = new Foo(1);
        assertThat(anotherFoo.bar(), equalTo("hello world"));
        assertThat(anotherFoo.baz(), equalTo(42));
    }


}
