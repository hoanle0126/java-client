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

import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.remote.options.CanSetCapability;
import org.openqa.selenium.Capabilities;

import java.util.Optional;

import static io.appium.java_client.internal.CapabilityHelpers.toSafeBoolean;

/**
 * Provides getters and setters for the {@code showSafariConsoleLog} capability.
 *
 * @param <T> options type, used for chaining.
 */
public interface SupportsShowSafariConsoleLogOption<T extends BaseOptions<T>> extends
        Capabilities, CanSetCapability<T> {
    /**
     * Name of the {@code showSafariConsoleLog} capability.
     */
    String SHOW_SAFARI_CONSOLE_LOG_OPTION = "showSafariConsoleLog";

    /**
     * Enable logging Safari JavaScript console events to Appium server logs and the
     * {@code safariConsole} log bucket.
     *
     * @return self instance for chaining.
     */
    default T showSafariConsoleLog() {
        return amend(SHOW_SAFARI_CONSOLE_LOG_OPTION, true);
    }

    /**
     * Control Safari JavaScript console log collection. When {@code true}, console
     * events are added to Appium server logs and written to the {@code safariConsole}
     * log bucket. When {@code false}, events are written only to the
     * {@code safariConsole} bucket without server logging. When unset, no console
     * events are collected (since XCUITest driver 7.22.0).
     *
     * @param value Whether to also emit Safari console events to Appium server logs.
     * @return self instance for chaining.
     */
    default T setShowSafariConsoleLog(boolean value) {
        return amend(SHOW_SAFARI_CONSOLE_LOG_OPTION, value);
    }

    /**
     * Get whether Safari JavaScript console events are emitted to Appium server logs.
     *
     * @return True or false.
     */
    default Optional<Boolean> doesShowSafariConsoleLog() {
        return Optional.ofNullable(toSafeBoolean(getCapability(SHOW_SAFARI_CONSOLE_LOG_OPTION)));
    }
}
