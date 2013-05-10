package org.perl6.rakudo;

import org.perl6.nqp.runtime.ThreadContext;
import org.perl6.nqp.sixmodel.SixModelObject;

/**
 * Contains implementation of nqp:: ops specific to Rakudo Perl 6.
 */
public final class Ops {
    private static SixModelObject False;
    private static SixModelObject True;
    private static boolean initialized = false;
    
    public static SixModelObject p6init(ThreadContext tc) {
        if (!initialized) {
            tc.gc.contConfigs.put("rakudo_scalar", new RakudoContainerConfigurer());
            initialized = true;
        }
        return null;
    }
    
    public static SixModelObject p6settypes(SixModelObject conf, ThreadContext tc) {
        False = conf.at_key_boxed(tc, "False");
        True = conf.at_key_boxed(tc, "True");
        return conf;
    }
    
    public static SixModelObject booleanize(int x) {
        return x == 0 ? False : True;
    }
}