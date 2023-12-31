/*
 *
 * Nextcloud Android client application
 *
 * @author Tobias Kaminsky
 * Copyright (C) 2020 Tobias Kaminsky
 * Copyright (C) 2020 Nextcloud GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.owncloud.android.ui.dialog

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.nextcloud.test.TestActivity
import com.owncloud.android.AbstractIT
import com.owncloud.android.utils.ScreenshotTest
import org.junit.Rule
import org.junit.Test

class SetupEncryptionDialogFragmentIT : AbstractIT() {
    @get:Rule
    val testActivityRule = IntentsTestRule(TestActivity::class.java, true, false)

    @Test
    @ScreenshotTest
    fun showMnemonic() {
        val activity = testActivityRule.launchActivity(null)

        val sut = SetupEncryptionDialogFragment.newInstance(user, 0)

        sut.show(activity.supportFragmentManager, "1")

        val keyWords = arrayListOf(
            "ability",
            "able",
            "about",
            "above",
            "absent",
            "absorb",
            "abstract",
            "absurd",
            "abuse",
            "access",
            "accident",
            "account",
            "accuse"
        )

        shortSleep()

        runOnUiThread {
            sut.setMnemonic(keyWords)
            sut.showMnemonicInfo()
        }

        waitForIdleSync()

        screenshot(sut.requireDialog().window!!.decorView)
    }

    @Test
    @ScreenshotTest
    fun error() {
        val activity = testActivityRule.launchActivity(null)

        val sut = SetupEncryptionDialogFragment.newInstance(user, 0)

        sut.show(activity.supportFragmentManager, "1")

        shortSleep()

        runOnUiThread {
            sut.errorSavingKeys()
        }

        shortSleep()
        waitForIdleSync()

        screenshot(sut.requireDialog().window!!.decorView)
    }
}
