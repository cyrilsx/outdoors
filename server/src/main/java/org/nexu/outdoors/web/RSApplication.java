package org.nexu.outdoors.web;


import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Call to configuration Jersey.
 */
public class RSApplication extends ResourceConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());


    public RSApplication() {
        logger.info("RSApplication()");
        packages("org.nexu.outdoors.web.resource", "org.nexu.outdoors.web.oauth");
    }

}
