/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.java_client.ios.options.simulator;

import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.remote.options.CanSetCapability;
import org.openqa.selenium.Capabilities;

import java.util.Map;
import java.util.Optional;

/**
 * Provides getters and setters for the {@code safariGlobalPreferences} capability.
 *
 * @param <T> options type, used for chaining.
 */
public interface SupportsSafariGlobalPreferencesOption<T extends BaseOptions<T>> extends
        Capabilities, CanSetCapability<T> {
    /**
     * Name of the {@code safariGlobalPreferences} capability.
     */
    String SAFARI_GLOBAL_PREFERENCES_OPTION = "safariGlobalPreferences";

    /**
     * Set Mobile Safari preferences to apply at session startup (for example
     * {@code ShowTabBar} or {@code WarnAboutFraudulentWebsites}). Available on real
     * devices since XCUITest driver 7.9.0; a new Safari instance must be launched
     * upon test startup for this capability to take effect on real devices.
     *
     * @param preferences Map of Safari preference keys and values.
     * @return self instance for chaining.
     */
    default T setSafariGlobalPreferences(Map<String, ?> preferences) {
        return amend(SAFARI_GLOBAL_PREFERENCES_OPTION, preferences);
    }

    /**
     * Get the Mobile Safari preferences mapping.
     *
     * @return Map of Safari preference keys and values.
     */
    default Optional<Map<String, Object>> getSafariGlobalPreferences() {
        //noinspection unchecked
        return Optional.ofNullable(
                (Map<String, Object>) getCapability(SAFARI_GLOBAL_PREFERENCES_OPTION)
        );
    }
}
