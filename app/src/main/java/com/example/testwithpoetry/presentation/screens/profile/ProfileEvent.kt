package com.example.testwithpoetry.presentation.screens.profile

sealed class ProfileEvent {
    data object GetUserInfo : ProfileEvent()
    data object SignOutUser : ProfileEvent()
}