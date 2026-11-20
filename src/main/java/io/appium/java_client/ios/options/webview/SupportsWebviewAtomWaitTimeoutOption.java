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

package io.appium.java_client.ios.options.webview;

import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.remote.options.CanSetCapability;
import org.openqa.selenium.Capabilities;

import java.time.Duration;
import java.util.Optional;

/**
 * Provides getters and setters for the {@code webviewAtomWaitTimeout} capability.
 *
 * @param <T> options type, used for chaining.
 */
public interface SupportsWebviewAtomWaitTimeoutOption<T extends BaseOptions<T>> extends
        Capabilities, CanSetCapability<T> {
    /**
     * Name of the {@code webviewAtomWaitTimeout} capability.
     */
    String WEBVIEW_ATOM_WAIT_TIMEOUT_OPTION = "webviewAtomWaitTimeout";

    /**
     * Set the final time budget in milliseconds for any atom script interacting with
     * a webview or Safari page. Defaults to {@code 120000} ms (values less than or
     * equal to zero fall back to the default). Also limits {@code execute} and
     * {@code executeAsync} (or only {@code executeAsync} when
     * {@code mobile: startAutomationSession} is active).
     *
     * @param timeout Atom wait timeout.
     * @return self instance for chaining.
     */
    default T setWebviewAtomWaitTimeout(Duration timeout) {
        return amend(WEBVIEW_ATOM_WAIT_TIMEOUT_OPTION, timeout.toMillis());
    }

    /**
     * Get the time budget for webview atom scripts.
     *
     * @return Atom wait timeout.
     */
    default Optional<Duration> getWebviewAtomWaitTimeout() {
        return Optional.ofNullable(
                CapabilityHelpers.toDuration(getCapability(WEBVIEW_ATOM_WAIT_TIMEOUT_OPTION))
        );
    }
}
