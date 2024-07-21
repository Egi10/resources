package com.example.resource

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

interface Platform {
    val name: String
}

fun getMyString(stringResource: StringResource): StringDesc {
    return StringDesc.Resource(stringResource)
}

expect fun getPlatform(): Platform