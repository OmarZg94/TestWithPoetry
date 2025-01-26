package com.example.testwithpoetry.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testwithpoetry.R
import com.example.testwithpoetry.presentation.theme.SizeMd
import com.example.testwithpoetry.presentation.theme.SizeSm
import com.example.testwithpoetry.utils.EMPTY
import com.example.testwithpoetry.utils.convertMillisToDate

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToWelcome: () -> Unit
) {
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(ProfileEvent.GetUserInfo)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SizeMd)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = profileState.user.name,
                onValueChange = { },
                label = { Text(stringResource(R.string.label_name)) },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(SizeSm))

            OutlinedTextField(
                value = profileState.user.email,
                onValueChange = { },
                label = { Text(stringResource(R.string.label_email)) },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(SizeSm))

            val birthDate =
                if (profileState.user.birthday > 0) profileState.user.birthday.convertMillisToDate() else EMPTY
            OutlinedTextField(
                value = birthDate,
                onValueChange = { },
                label = { Text(stringResource(R.string.label_dob)) },
                readOnly = true,
                trailingIcon = {
                    Image(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = stringResource(R.string.content_description_select_date)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
        Button(
            onClick = {
                viewModel.onEvent(ProfileEvent.SignOutUser)
                onNavigateToWelcome()
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = SizeMd)
        ) {
            Text(stringResource(R.string.label_sign_out))
        }
    }
}