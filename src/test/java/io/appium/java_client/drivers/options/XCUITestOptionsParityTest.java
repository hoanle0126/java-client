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

package io.appium.java_client.drivers.options;

import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.app.SupportsAppTimeZoneOption;
import io.appium.java_client.ios.options.app.SupportsInitialDeeplinkUrlOption;
import io.appium.java_client.ios.options.other.SupportsIosSyslogFileOption;
import io.appium.java_client.ios.options.simulator.SupportsAutoFillPasswordsOption;
import io.appium.java_client.ios.options.simulator.SupportsReduceTransparencyOption;
import io.appium.java_client.ios.options.simulator.SupportsSafariGlobalPreferencesOption;
import io.appium.java_client.ios.options.simulator.SupportsSimulatorLogLevelOption;
import io.appium.java_client.ios.options.simulator.SupportsSkipSyncUiDialogTranslationOption;
import io.appium.java_client.ios.options.wda.SupportsAppLaunchStateTimeoutSecOption;
import io.appium.java_client.ios.options.wda.SupportsAutoLaunchOption;
import io.appium.java_client.ios.options.wda.SupportsIosInstallPauseOption;
import io.appium.java_client.ios.options.wda.SupportsMjpegScreenshotUrlOption;
import io.appium.java_client.ios.options.wda.SupportsUpdatedWdaBundleIdSuffixOption;
import io.appium.java_client.ios.options.wda.SupportsWdaBindingIpOption;
import io.appium.java_client.ios.options.wda.SupportsWdaRemotePortOption;
import io.appium.java_client.ios.options.webview.SupportsIgnoredWebviewBundleIdsOption;
import io.appium.java_client.ios.options.webview.SupportsShowSafariConsoleLogOption;
import io.appium.java_client.ios.options.webview.SupportsShowSafariNetworkLogOption;
import io.appium.java_client.ios.options.webview.SupportsSkipTriggerInputEventAfterSendkeysOption;
import io.appium.java_client.ios.options.webview.SupportsWebviewAtomWaitTimeoutOption;
import io.appium.java_client.remote.options.SupportsPageLoadStrategyOption;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.ImmutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XCUITestOptionsParityTest {

    @Test
    void xcuitestNewOptionsDefaultToEmpty() {
        var options = new XCUITestOptions();
        assertEquals(Optional.empty(), options.getInitialDeeplinkUrl());
        assertEquals(Optional.empty(), options.getAppTimeZone());
        assertEquals(Optional.empty(), options.getAppLaunchStateTimeoutSec());
        assertEquals(Optional.empty(), options.doesAutoLaunch());
        assertEquals(Optional.empty(), options.getIosInstallPause());
        assertEquals(Optional.empty(), options.getMjpegScreenshotUrl());
        assertEquals(Optional.empty(), options.getUpdatedWdaBundleIdSuffix());
        assertEquals(Optional.empty(), options.getWdaBindingIp());
        assertEquals(Optional.empty(), options.getWdaRemotePort());
        assertEquals(Optional.empty(), options.doesAutoFillPasswords());
        assertEquals(Optional.empty(), options.doesReduceTransparency());
        assertEquals(Optional.empty(), options.getSafariGlobalPreferences());
        assertEquals(Optional.empty(), options.getSimulatorLogLevel());
        assertEquals(Optional.empty(), options.doesSkipSyncUiDialogTranslation());
        assertEquals(Optional.empty(), options.getPageLoadStrategy());
        assertEquals(Optional.empty(), options.getIgnoredWebviewBundleIds());
        assertEquals(Optional.empty(), options.getWebviewAtomWaitTimeout());
        assertEquals(Optional.empty(), options.doesSkipTriggerInputEventAfterSendkeys());
        assertEquals(Optional.empty(), options.doesShowSafariConsoleLog());
        assertEquals(Optional.empty(), options.doesShowSafariNetworkLog());
        assertEquals(Optional.empty(), options.getIosSyslogFile());
    }

    @Test
    void xcuitestNewOptionsSetGetAndSerializeW3cKeys() throws MalformedURLException {
        var safariPrefs = Map.of("ShowTabBar", 0, "WarnAboutFraudulentWebsites", 0);
        var ignoredWebviews = List.of("com.apple.amsengagementd", "com.apple.springboard");
        var mjpegUrl = URI.create("http://127.0.0.1:9100").toURL();

        var options = new XCUITestOptions()
                .setInitialDeeplinkUrl("myapp://open/home")
                .setAppTimeZone("America/Los_Angeles")
                .setAppLaunchStateTimeoutSec(Duration.ofMillis(10_500))
                .disableAutoLaunch()
                .setIosInstallPause(Duration.ofSeconds(8))
                .setMjpegScreenshotUrl(mjpegUrl)
                .setUpdatedWdaBundleIdSuffix("")
                .setWdaBindingIp("127.0.0.1")
                .setWdaRemotePort(8200)
                .autoFillPasswords()
                .reduceTransparency()
                .setSafariGlobalPreferences(safariPrefs)
                .setSimulatorLogLevel("debug")
                .skipSyncUiDialogTranslation()
                .setPageLoadStrategy(PageLoadStrategy.EAGER)
                .setIgnoredWebviewBundleIds(ignoredWebviews)
                .setWebviewAtomWaitTimeout(Duration.ofSeconds(20))
                .skipTriggerInputEventAfterSendkeys()
                .showSafariConsoleLog()
                .showSafariNetworkLog()
                .setIosSyslogFile("/tmp/ios-syslog.log");

        assertEquals(Optional.of("myapp://open/home"), options.getInitialDeeplinkUrl());
        assertEquals(Optional.of("America/Los_Angeles"), options.getAppTimeZone());
        assertEquals(Optional.of(Duration.ofMillis(10_500)), options.getAppLaunchStateTimeoutSec());
        assertEquals(Optional.of(false), options.doesAutoLaunch());
        assertEquals(Optional.of(Duration.ofSeconds(8)), options.getIosInstallPause());
        assertEquals(Optional.of(mjpegUrl), options.getMjpegScreenshotUrl());
        assertEquals(Optional.of(""), options.getUpdatedWdaBundleIdSuffix());
        assertEquals(Optional.of("127.0.0.1"), options.getWdaBindingIp());
        assertEquals(Optional.of(8200), options.getWdaRemotePort());
        assertTrue(options.doesAutoFillPasswords().orElse(false));
        assertTrue(options.doesReduceTransparency().orElse(false));
        assertEquals(Optional.of(safariPrefs), options.getSafariGlobalPreferences());
        assertEquals(Optional.of("debug"), options.getSimulatorLogLevel());
        assertTrue(options.doesSkipSyncUiDialogTranslation().orElse(false));
        assertEquals(Optional.of(PageLoadStrategy.EAGER), options.getPageLoadStrategy());
        assertEquals(Optional.of(ignoredWebviews), options.getIgnoredWebviewBundleIds());
        assertEquals(Optional.of(Duration.ofSeconds(20)), options.getWebviewAtomWaitTimeout());
        assertTrue(options.doesSkipTriggerInputEventAfterSendkeys().orElse(false));
        assertTrue(options.doesShowSafariConsoleLog().orElse(false));
        assertTrue(options.doesShowSafariNetworkLog().orElse(false));
        assertEquals(Optional.of("/tmp/ios-syslog.log"), options.getIosSyslogFile());

        var serialized = options.asMap();
        assertEquals("myapp://open/home",
                serialized.get("appium:" + SupportsInitialDeeplinkUrlOption.INITIAL_DEEPLINK_URL_OPTION));
        assertEquals("America/Los_Angeles",
                serialized.get("appium:" + SupportsAppTimeZoneOption.APP_TIME_ZONE_OPTION));
        assertEquals(10.5,
                serialized.get("appium:" + SupportsAppLaunchStateTimeoutSecOption.APP_LAUNCH_STATE_TIMEOUT_SEC_OPTION));
        assertEquals(false,
                serialized.get("appium:" + SupportsAutoLaunchOption.AUTO_LAUNCH_OPTION));
        assertEquals(8000L,
                serialized.get("appium:" + SupportsIosInstallPauseOption.IOS_INSTALL_PAUSE_OPTION));
        assertEquals("http://127.0.0.1:9100",
                serialized.get("appium:" + SupportsMjpegScreenshotUrlOption.MJPEG_SCREENSHOT_URL_OPTION));
        assertEquals("",
                serialized.get("appium:" + SupportsUpdatedWdaBundleIdSuffixOption.UPDATED_WDA_BUNDLE_ID_SUFFIX_OPTION));
        assertEquals("127.0.0.1",
                serialized.get("appium:" + SupportsWdaBindingIpOption.WDA_BINDING_IP_OPTION));
        assertEquals(8200,
                serialized.get("appium:" + SupportsWdaRemotePortOption.WDA_REMOTE_PORT_OPTION));
        assertEquals(true,
                serialized.get("appium:" + SupportsAutoFillPasswordsOption.AUTO_FILL_PASSWORDS_OPTION));
        assertEquals(true,
                serialized.get("appium:" + SupportsReduceTransparencyOption.REDUCE_TRANSPARENCY_OPTION));
        assertEquals(safariPrefs,
                serialized.get("appium:" + SupportsSafariGlobalPreferencesOption.SAFARI_GLOBAL_PREFERENCES_OPTION));
        assertEquals("debug",
                serialized.get("appium:" + SupportsSimulatorLogLevelOption.SIMULATOR_LOG_LEVEL_OPTION));
        assertEquals(true,
                serialized.get("appium:"
                        + SupportsSkipSyncUiDialogTranslationOption.SKIP_SYNC_UI_DIALOG_TRANSLATION_OPTION));
        assertEquals("eager",
                serialized.get(SupportsPageLoadStrategyOption.PAGE_LOAD_STRATEGY_OPTION));
        assertEquals(ignoredWebviews,
                serialized.get("appium:" + SupportsIgnoredWebviewBundleIdsOption.IGNORED_WEBVIEW_BUNDLE_IDS_OPTION));
        assertEquals(20_000L,
                serialized.get("appium:" + SupportsWebviewAtomWaitTimeoutOption.WEBVIEW_ATOM_WAIT_TIMEOUT_OPTION));
        assertEquals(true,
                serialized.get("appium:"
                        + SupportsSkipTriggerInputEventAfterSendkeysOption
                                .SKIP_TRIGGER_INPUT_EVENT_AFTER_SENDKEYS_OPTION));
        assertEquals(true,
                serialized.get("appium:" + SupportsShowSafariConsoleLogOption.SHOW_SAFARI_CONSOLE_LOG_OPTION));
        assertEquals(true,
                serialized.get("appium:" + SupportsShowSafariNetworkLogOption.SHOW_SAFARI_NETWORK_LOG_OPTION));
        assertEquals("/tmp/ios-syslog.log",
                serialized.get("appium:" + SupportsIosSyslogFileOption.IOS_SYSLOG_FILE_OPTION));
    }

    @Test
    void booleanSettersAndStringUrlOverloadToggleCorrectly() throws MalformedURLException {
        var options = new XCUITestOptions()
                .setAutoLaunch(false)
                .setAutoFillPasswords(false)
                .setReduceTransparency(false)
                .setSkipSyncUiDialogTranslation(false)
                .setSkipTriggerInputEventAfterSendkeys(false)
                .setShowSafariConsoleLog(false)
                .setShowSafariNetworkLog(false)
                .setMjpegScreenshotUrl("http://10.0.0.2:9100/stream");

        assertEquals(Optional.of(false), options.doesAutoLaunch());
        assertEquals(Optional.of(false), options.doesAutoFillPasswords());
        assertEquals(Optional.of(false), options.doesReduceTransparency());
        assertEquals(Optional.of(false), options.doesSkipSyncUiDialogTranslation());
        assertEquals(Optional.of(false), options.doesSkipTriggerInputEventAfterSendkeys());
        assertEquals(Optional.of(false), options.doesShowSafariConsoleLog());
        assertEquals(Optional.of(false), options.doesShowSafariNetworkLog());
        assertEquals(Optional.of(URI.create("http://10.0.0.2:9100/stream").toURL()),
                options.getMjpegScreenshotUrl());

        options.setAutoLaunch(true)
                .setAutoFillPasswords(true)
                .setReduceTransparency(true)
                .setSkipSyncUiDialogTranslation(true)
                .setSkipTriggerInputEventAfterSendkeys(true)
                .setShowSafariConsoleLog(true)
                .setShowSafariNetworkLog(true);

        assertTrue(options.doesAutoLaunch().orElse(false));
        assertTrue(options.doesAutoFillPasswords().orElse(false));
        assertTrue(options.doesReduceTransparency().orElse(false));
        assertTrue(options.doesSkipSyncUiDialogTranslation().orElse(false));
        assertTrue(options.doesSkipTriggerInputEventAfterSendkeys().orElse(false));
        assertTrue(options.doesShowSafariConsoleLog().orElse(false));
        assertTrue(options.doesShowSafariNetworkLog().orElse(false));
    }

    @Test
    void copyConstructorsCoerceAndPreserveNewOptions() throws MalformedURLException {
        var prefs = Map.of("ShowTabBar", 1);
        var ignored = List.of("com.apple.amsengagementd");

        var fromMap = new XCUITestOptions(Map.ofEntries(
                Map.entry("initialDeeplinkUrl", "https://appium.io"),
                Map.entry("appium:appTimeZone", "UTC"),
                Map.entry("appLaunchStateTimeoutSec", "12.25"),
                Map.entry("appium:autoLaunch", "false"),
                Map.entry("iosInstallPause", "5000"),
                Map.entry("appium:mjpegScreenshotUrl", "http://127.0.0.1:9200"),
                Map.entry("updatedWDABundleIdSuffix", ".custom"),
                Map.entry("appium:wdaBindingIP", "127.0.0.2"),
                Map.entry("wdaRemotePort", "8300"),
                Map.entry("appium:autoFillPasswords", "true"),
                Map.entry("reduceTransparency", true),
                Map.entry("appium:safariGlobalPreferences", prefs),
                Map.entry("simulatorLogLevel", "info"),
                Map.entry("appium:skipSyncUiDialogTranslation", "true"),
                Map.entry("pageLoadStrategy", "none"),
                Map.entry("appium:ignoredWebviewBundleIds", ignored),
                Map.entry("webviewAtomWaitTimeout", "30000"),
                Map.entry("appium:skipTriggerInputEventAfterSendkeys", "true"),
                Map.entry("showSafariConsoleLog", "false"),
                Map.entry("appium:showSafariNetworkLog", "true"),
                Map.entry("iosSyslogFile", "/var/log/sim.log")
        ));

        assertEquals(Optional.of("https://appium.io"), fromMap.getInitialDeeplinkUrl());
        assertEquals(Optional.of("UTC"), fromMap.getAppTimeZone());
        assertEquals(Optional.of(Duration.ofMillis(12_250)), fromMap.getAppLaunchStateTimeoutSec());
        assertEquals(Optional.of(false), fromMap.doesAutoLaunch());
        assertEquals(Optional.of(Duration.ofSeconds(5)), fromMap.getIosInstallPause());
        assertEquals(Optional.of(URI.create("http://127.0.0.1:9200").toURL()),
                fromMap.getMjpegScreenshotUrl());
        assertEquals(Optional.of(".custom"), fromMap.getUpdatedWdaBundleIdSuffix());
        assertEquals(Optional.of("127.0.0.2"), fromMap.getWdaBindingIp());
        assertEquals(Optional.of(8300), fromMap.getWdaRemotePort());
        assertEquals(Optional.of(true), fromMap.doesAutoFillPasswords());
        assertEquals(Optional.of(true), fromMap.doesReduceTransparency());
        assertEquals(Optional.of(prefs), fromMap.getSafariGlobalPreferences());
        assertEquals(Optional.of("info"), fromMap.getSimulatorLogLevel());
        assertEquals(Optional.of(true), fromMap.doesSkipSyncUiDialogTranslation());
        assertEquals(Optional.of(PageLoadStrategy.NONE), fromMap.getPageLoadStrategy());
        assertEquals(Optional.of(ignored), fromMap.getIgnoredWebviewBundleIds());
        assertEquals(Optional.of(Duration.ofSeconds(30)), fromMap.getWebviewAtomWaitTimeout());
        assertEquals(Optional.of(true), fromMap.doesSkipTriggerInputEventAfterSendkeys());
        assertEquals(Optional.of(false), fromMap.doesShowSafariConsoleLog());
        assertEquals(Optional.of(true), fromMap.doesShowSafariNetworkLog());
        assertEquals(Optional.of("/var/log/sim.log"), fromMap.getIosSyslogFile());

        var fromCaps = new XCUITestOptions(new ImmutableCapabilities(Map.of(
                "appium:appLaunchStateTimeoutSec", 1.13,
                "appium:appTimeZone", "Europe/London",
                "appium:wdaRemotePort", 8101,
                "pageLoadStrategy", "eager"
        )));
        assertEquals(Optional.of(Duration.ofMillis(1130)), fromCaps.getAppLaunchStateTimeoutSec());
        assertEquals(Optional.of("Europe/London"), fromCaps.getAppTimeZone());
        assertEquals(Optional.of(8101), fromCaps.getWdaRemotePort());
        assertEquals(Optional.of(PageLoadStrategy.EAGER), fromCaps.getPageLoadStrategy());
    }
}
