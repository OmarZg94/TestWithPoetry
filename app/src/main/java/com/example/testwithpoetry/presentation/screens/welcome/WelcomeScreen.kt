package com.example.testwithpoetry.presentation.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testwithpoetry.R
import com.example.testwithpoetry.presentation.components.DatePickerModal
import com.example.testwithpoetry.presentation.theme.SizeMd
import com.example.testwithpoetry.presentation.theme.SizeSm
import com.example.testwithpoetry.utils.EMPTY
import com.example.testwithpoetry.utils.convertMillisToDate

const val WELCOME_DESTINATION = "welcome"

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    onNavigateToMain: (String) -> Unit
) {
    val welcomeState by viewModel.welcomeState.collectAsStateWithLifecycle()

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
            Text(
                text = stringResource(R.string.label_welcome),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(SizeMd))

            OutlinedTextField(
                value = welcomeState.name,
                onValueChange = { viewModel.onEvent(WelcomeEvent.UserChanged(it)) },
                label = { Text(stringResource(R.string.label_name)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(SizeSm))

            OutlinedTextField(
                value = welcomeState.email,
                onValueChange = { viewModel.onEvent(WelcomeEvent.EmailChanged(it)) },
                label = { Text(stringResource(R.string.label_email)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(SizeSm))

            val birthDate =
                if (welcomeState.birthday > 0) welcomeState.birthday.convertMillisToDate() else EMPTY
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
                    .pointerInput(welcomeState.birthday) {
                        awaitEachGesture {
                            awaitFirstDown(pass = PointerEventPass.Initial)
                            val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                            if (upEvent != null) {
                                viewModel.onEvent(WelcomeEvent.ShowDateModal(true))
                            }
                        }
                    }
            )

        }
        Button(
            onClick = {
                viewModel.onEvent(WelcomeEvent.SaveUser)
                onNavigateToMain.invoke(welcomeState.name)
            },
            enabled = welcomeState.isFormValid,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = SizeMd)
        ) {
            Text(stringResource(R.string.go_to_authors_list))
        }

        if (welcomeState.showModal) {
            DatePickerModal(
                onDateSelected = { viewModel.onEvent(WelcomeEvent.BirthdayChanged(it)) },
                onDismiss = {
                    viewModel.onEvent(WelcomeEvent.ShowDateModal(false))
                }
            )
        }
    }
}