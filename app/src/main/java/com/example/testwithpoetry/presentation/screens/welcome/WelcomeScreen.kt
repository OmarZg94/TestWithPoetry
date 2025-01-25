package com.example.testwithpoetry.presentation.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.testwithpoetry.R
import com.example.testwithpoetry.domain.models.User
import com.example.testwithpoetry.presentation.components.DatePickerModal
import com.example.testwithpoetry.presentation.theme.SizeMd
import com.example.testwithpoetry.presentation.theme.SizeSm
import com.example.testwithpoetry.utils.EMPTY
import com.example.testwithpoetry.utils.convertMillisToDate

const val WELCOME_DESTINATION = "welcome"

@Composable
fun WelcomeScreen(
    onNavigateToAuthors: (User) -> Unit
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf(EMPTY) }
    var email by remember { mutableStateOf(EMPTY) }
    var birthday by remember { mutableLongStateOf(0L) }
    var showModal by remember { mutableStateOf(false) }
    val isFormValid = name.isNotBlank() && email.isNotBlank() && birthday > 0L

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SizeMd),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.label_welcome),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(SizeMd))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.label_name)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(SizeSm))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.label_email)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(SizeMd))

        OutlinedTextField(
            value = if (birthday > 0) birthday.convertMillisToDate() else EMPTY,
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
                .pointerInput(birthday) {
                    awaitEachGesture {
                        awaitFirstDown(pass = PointerEventPass.Initial)
                        val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                        if (upEvent != null) {
                            showModal = true
                        }
                    }
                }
        )
        Spacer(modifier = Modifier.height(SizeMd))

        Button(
            onClick = {
                onNavigateToAuthors(
                    User(name = name, email = email, birthday = birthday)
                )
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.go_to_authors_list))
        }

        if (showModal) {
            DatePickerModal(
                onDateSelected = { birthday = it },
                onDismiss = { showModal = false }
            )
        }
    }
}