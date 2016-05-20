import org.junit.Test;

public class MethodReferenceTest {
    interface TestListener {
        void onClick();
    }

    private void doIt(TestListener listener) {
        listener.onClick();
    }

    @Test
    public void pass_methodReference_for_sam() throws Exception {
//        doIt(this::method_instead_of_interface_impl);
        // works if interface is SAM(functional interface)
    }

    private void method_instead_of_interface_impl() {
        System.out.println("works");
    }
}
