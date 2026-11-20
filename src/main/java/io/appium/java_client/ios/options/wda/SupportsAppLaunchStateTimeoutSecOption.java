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

package io.appium.java_client.ios.options.wda;

import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.remote.options.CanSetCapability;
import org.openqa.selenium.Capabilities;

import java.time.Duration;
import java.util.Optional;

/**
 * Provides getters and setters for the {@code appLaunchStateTimeoutSec} capability.
 *
 * @param <T> options type, used for chaining.
 */
public interface SupportsAppLaunchStateTimeoutSecOption<T extends BaseOptions<T>> extends
        Capabilities, CanSetCapability<T> {
    /**
     * Name of the {@code appLaunchStateTimeoutSec} capability.
     */
    String APP_LAUNCH_STATE_TIMEOUT_SEC_OPTION = "appLaunchStateTimeoutSec";

    /**
     * Set the timeout in float seconds (in the exclusive range {@code (0, 240)})
     * to wait for the application state change during session startup. XCTest
     * defaults to 60 seconds. Has no effect if the application under test is not
     * (re)started at session startup.
     *
     * @param timeout Startup state change timeout.
     * @return self instance for chaining.
     */
    default T setAppLaunchStateTimeoutSec(Duration timeout) {
        return amend(APP_LAUNCH_STATE_TIMEOUT_SEC_OPTION, timeout.toMillis() / 1000.0);
    }

    /**
     * Get the application startup state change timeout.
     *
     * @return Startup state change timeout.
     */
    default Optional<Duration> getAppLaunchStateTimeoutSec() {
        return Optional.ofNullable(getCapability(APP_LAUNCH_STATE_TIMEOUT_SEC_OPTION))
                .map(CapabilityHelpers::toDouble)
                .map(d -> Duration.ofMillis(Math.round(d * 1000.0)));
    }
}
