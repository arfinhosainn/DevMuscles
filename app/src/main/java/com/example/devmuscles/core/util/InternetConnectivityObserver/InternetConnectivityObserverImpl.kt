package com.example.devmuscles.core.util.InternetConnectivityObserver

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import logcat.logcat

class InternetConnectivityObserverImpl(
    private val context: Context
) : InternetConnectivityObserver {


    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override val onlineStateFlow = connectivityFlow().combine(
        internetReachabilityFlow
    ) { connectedStatus, internetReachableStatus ->
        if (connectedStatus == InternetConnectivityObserver.ConnectivityStatus.Available
            && internetReachableStatus == InternetConnectivityObserver.InternetReachabilityStatus.REACHABLE
        )
            InternetConnectivityObserver.OnlineStatus.ONLINE
        else
            InternetConnectivityObserver.OnlineStatus.OFFLINE
    }.distinctUntilChanged()

    companion object {
        val internetReachabilityFlow =
            MutableStateFlow(InternetConnectivityObserver.InternetReachabilityStatus.UNREACHABLE)

        val isInternetReachable: Boolean
            get() {
                val runtime = Runtime.getRuntime()
                try {
                    logcat { "isInternetReachable() - ⎾ Checking Internet Reachability with a ping..." }

                    val ipProcess = runtime.exec("/system/bin/ping -W 800 -c 1 8.8.8.8")

                    val exitValue = ipProcess.waitFor()

                    logcat { "isInternetReachable() - ⎿ Internet Reachability=${exitValue == 0}" }

                    internetReachabilityFlow.value =
                        if (exitValue == 0)

                            InternetConnectivityObserver.InternetReachabilityStatus.REACHABLE
                        else
                            InternetConnectivityObserver.InternetReachabilityStatus.UNREACHABLE

                    return (exitValue == 0)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                internetReachabilityFlow.value =
                    InternetConnectivityObserver.InternetReachabilityStatus.UNREACHABLE
                return false
            }
    }

    override fun connectivityFlow(): Flow<InternetConnectivityObserver.ConnectivityStatus> {
        return callbackFlow<InternetConnectivityObserver.ConnectivityStatus> {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    logcat { "NetworkCallback - Connectivity onAvailable" }
                    isInternetReachable
                    trySend(InternetConnectivityObserver.ConnectivityStatus.Available)
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    logcat { "NetworkCallback - Connectivity onLosing" }
                    trySend(InternetConnectivityObserver.ConnectivityStatus.Losing)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    logcat { "NetworkCallback - Connectivity onLost" }
                    trySend(InternetConnectivityObserver.ConnectivityStatus.Lost)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    logcat { "NetworkCallback - Connectivity onUnavailable" }
                    trySend(InternetConnectivityObserver.ConnectivityStatus.Unavailable)
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}