package com.laicamist.tipsboostedv4

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform