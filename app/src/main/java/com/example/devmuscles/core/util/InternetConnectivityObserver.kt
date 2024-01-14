package com.example.devmuscles.core.util

import kotlinx.coroutines.flow.Flow

interface InternetConnectivityObserver {

    val onlineStateFlow: Flow<OnlineStatus>

    fun connectivityFlow(): Flow<ConnectivityStatus>

    enum class OnlineStatus {
        ONLINE,
        OFFLINE
    }

    enum class InternetReachabilityStatus {
        REACHABLE,
        UNREACHABLE,
    }

    enum class ConnectivityStatus {
        Available, Unavailable, Losing, Lost
    }
}