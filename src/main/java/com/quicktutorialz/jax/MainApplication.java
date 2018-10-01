package com.quicktutorialz.jax;

public class MainApplication {

    public static void main(String[] args) throws Exception {
        EmbeddedJetty jetty = new EmbeddedJetty()
                                    .setServerPort(8282)
                                    .setPackageToScan("com.quicktutorialz.jax.endpoints")
                                    .start();
    }
}
