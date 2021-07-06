package test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.Permission;

import org.junit.Assert;

import atm.MakeTransaction;

public class TestBase {
    protected static final String SEPARATOR = System.lineSeparator();
    private final ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final SecurityManager defaultSecurityManager = System
            .getSecurityManager();
    private final PrintStream err = System.err;
    private final PrintStream out = System.out;

    protected boolean hasExited = false;

    // TODO: Add a "rules" system to modifications to compared strings for better extensibility.
    //       Reconsider method name.
    public static void assertEquals(String expected, String actual) {
        String modifiedExpected = expected.replaceAll(SEPARATOR, "\n");
        String modifiedActual = actual.replaceAll(SEPARATOR, "\n");
        Assert.assertEquals(modifiedExpected, modifiedActual);
    }

    /**
     * This private class is used to "hijack" calls to {@link System#exit(int)}
     * in order to allow tests of {@link MakeTransaction#main(String[])}.
     * 
     * @author Zimon Kuhs
     * @since 2021-07-02
     */
    private class TestSecurityManager extends SecurityManager {
        @Override
        public void checkExit(int status) {
            throw new SecurityException();
        }

        @Override
        public void checkPermission(Permission perm) {
            // Allow other activities by default.
        }
    }

    /**
     * Retrieves the strings printed to {@link System.err} during a {@code #main(String[])} test.
     * <p>
     * N.B. implicitly redirects the {@link SecurityManager} via {@link #mainTest(String...)}
     * to allow for consecutive tests.
     * 
     * @param strings   The input arguments to the main method.
     * @return          All strings printed to the standard output stream.
     */
    protected String mainErrorTest(String... strings) {
        System.setErr(new PrintStream(errorStream));
        mainTest(strings);
        String result = errorStream.toString();
        System.setErr(err);
        //System.err.println(result);
        return result;
    }

    /**
     * Retrieves the strings printed to {@link System.out} during a {@code #main(String[])} test.
     * <p>
     * N.B. implicitly redirects the {@link SecurityManager} via {@link #mainTest(String...)}
     * to allow for consecutive tests.
     * 
     * @param strings   The input arguments to the main method.
     * @return          All strings printed to the standard output stream.
     */
    protected String mainOutputTest(String... strings) {
        System.setOut(new PrintStream(outStream));
        mainTest(strings);
        String result = outStream.toString();
        System.setOut(out);
        return result;
    }

    /**
     * Redirects the security manager while testing a class' #main(String[]) method.
     * <p>
     * This allows execution of consecutive main tests.
     * 
     * @param strings   The input arguments to the main method.
     */
    protected void mainTest(String... strings) {
        TestSecurityManager secManager = new TestSecurityManager();
        System.setSecurityManager(secManager);
        try {
            MakeTransaction.main(strings);
        } catch (SecurityException exception) {
            this.hasExited = true;
        }
        System.setSecurityManager(defaultSecurityManager);
    }
}
