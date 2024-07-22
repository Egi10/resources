package com.example.resources

import com.example.resource.getMyString
import org.example.library.resource.FeederRes

class IOSPlatform: Platform {
    override val name: String = getMyString(stringResource = FeederRes.strings.tests).localized()
}

actual fun getPlatform(): Platform = IOSPlatform()