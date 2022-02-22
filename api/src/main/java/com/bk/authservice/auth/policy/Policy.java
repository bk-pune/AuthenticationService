package com.bk.authservice.auth.policy;

import java.util.Properties;

/**
 * Marker interface for the policies.
 */
public interface Policy {
    Policy loadFromProperties(Properties properties);
}
