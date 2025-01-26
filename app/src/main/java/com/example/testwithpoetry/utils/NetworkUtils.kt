package com.example.testwithpoetry.utils

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

suspend fun <T> safeApiCall(apiCall: suspend  () -> T): T {
    return try {
        apiCall()
    } catch (e: ClientRequestException) {
        throw Exception("Client error: ${e.message}")
    } catch (e: ServerResponseException) {
        throw Exception("Server error: ${e.message}")
    } catch (e: Exception) {
        throw Exception("Unexpected error: ${e.message}")
    }
}