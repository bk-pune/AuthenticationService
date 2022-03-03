package com.bk.authservice.policy;

import java.util.Properties;

/**
 * Marker interface for the policies.
 */
public interface Policy {
    Policy loadFromProperties(Properties properties);
}
