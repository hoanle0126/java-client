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
 * Provides getters and setters for the {@code skipTriggerInputEventAfterSendkeys} capability.
 *
 * @param <T> options type, used for chaining.
 */
public interface SupportsSkipTriggerInputEventAfterSendkeysOption<T extends BaseOptions<T>> extends
        Capabilities, CanSetCapability<T> {
    /**
     * Name of the {@code skipTriggerInputEventAfterSendkeys} capability.
     */
    String SKIP_TRIGGER_INPUT_EVENT_AFTER_SENDKEYS_OPTION = "skipTriggerInputEventAfterSendkeys";

    /**
     * Skip firing an additional {@code input} event after Send Keys in a web context.
     *
     * @return self instance for chaining.
     */
    default T skipTriggerInputEventAfterSendkeys() {
        return amend(SKIP_TRIGGER_INPUT_EVENT_AFTER_SENDKEYS_OPTION, true);
    }

    /**
     * Specify whether to skip firing an additional {@code input} event on the target
     * input field after Send Keys in a web context. Defaults to {@code false}.
     *
     * @param value Whether to skip the extra {@code input} event.
     * @return self instance for chaining.
     */
    default T setSkipTriggerInputEventAfterSendkeys(boolean value) {
        return amend(SKIP_TRIGGER_INPUT_EVENT_AFTER_SENDKEYS_OPTION, value);
    }

    /**
     * Get whether to skip firing an additional {@code input} event after Send Keys.
     *
     * @return True or false.
     */
    default Optional<Boolean> doesSkipTriggerInputEventAfterSendkeys() {
        return Optional.ofNullable(
                toSafeBoolean(getCapability(SKIP_TRIGGER_INPUT_EVENT_AFTER_SENDKEYS_OPTION))
        );
    }
}
