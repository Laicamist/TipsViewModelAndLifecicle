package com.laicamist.tipsboostedv2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform