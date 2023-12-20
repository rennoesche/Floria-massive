package org.umi.floria.models

interface LoginCallback {
    fun onLoginSuccess()
    fun onLoginFailed()
}
