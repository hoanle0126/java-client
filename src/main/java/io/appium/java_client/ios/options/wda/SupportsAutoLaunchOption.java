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

import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.remote.options.CanSetCapability;
import org.openqa.selenium.Capabilities;

import java.util.Optional;

import static io.appium.java_client.internal.CapabilityHelpers.toSafeBoolean;

/**
 * Provides getters and setters for the {@code autoLaunch} capability.
 *
 * @param <T> options type, used for chaining.
 */
public interface SupportsAutoLaunchOption<T extends BaseOptions<T>> extends
        Capabilities, CanSetCapability<T> {
    /**
     * Name of the {@code autoLaunch} capability.
     */
    String AUTO_LAUNCH_OPTION = "autoLaunch";

    /**
     * Prevent the application under test from being launched automatically during
     * session startup. The app can then be started explicitly with
     * {@code mobile: launchApp} and {@code asAppUnderTest} enabled.
     *
     * @return self instance for chaining.
     */
    default T disableAutoLaunch() {
        return amend(AUTO_LAUNCH_OPTION, false);
    }

    /**
     * Specify whether the application under test is launched automatically as part
     * of session startup. Defaults to {@code true}. When set to {@code false},
     * use {@code mobile: launchApp} with {@code asAppUnderTest} enabled to launch
     * the app later.
     *
     * @param value Whether to auto-launch the application under test.
     * @return self instance for chaining.
     */
    default T setAutoLaunch(boolean value) {
        return amend(AUTO_LAUNCH_OPTION, value);
    }

    /**
     * Get whether the application under test is launched automatically on startup.
     *
     * @return True or false.
     */
    default Optional<Boolean> doesAutoLaunch() {
        return Optional.ofNullable(toSafeBoolean(getCapability(AUTO_LAUNCH_OPTION)));
    }
}
