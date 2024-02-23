package com.androbrain.weathercaster.ui.screen.locations.composable

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.androbrain.weathercaster.R
import com.androbrain.weathercaster.ui.theme.App
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener

@Composable
fun AddLocationItem(
    modifier: Modifier = Modifier,
    locationLoading: Boolean,
    latitude: Double?,
    longitude: Double?,
    @StringRes addError: Int?,
    setLatitude: (String) -> Unit,
    setLongitude: (String) -> Unit,
    setLoadingLocation: (Boolean) -> Unit,
    add: () -> Unit,
) {
    val context = LocalContext.current
    var hasLocationPermission by remember {
        mutableStateOf(
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        )
    }

    fun setLocation() {
        val locationClient = LocationServices.getFusedLocationProviderClient(context)
        setLoadingLocation(true)
        locationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) =
                    CancellationTokenSource().token

                override fun isCancellationRequested() = false
            }
        ).addOnSuccessListener { location ->
            setLoadingLocation(false)
            if (location == null) {
                Toast.makeText(context, R.string.locations_get_error, Toast.LENGTH_SHORT).show()
            } else {
                setLatitude(location.latitude.toString())
                setLongitude(location.longitude.toString())
            }
        }
    }

    val preciseLocationLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                hasLocationPermission = true
                setLocation()
            }

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                hasLocationPermission = true
                setLocation()
            }

            else -> {
                Toast.makeText(
                    context,
                    R.string.locations_get_permission_required,
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }

    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(App.dimens.viewSpacingSmall)
                .animateContentSize(),
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = latitude?.toString().orEmpty(),
                onValueChange = setLatitude,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next,
                ),
                label = { Text(text = stringResource(id = R.string.locations_add_latitude)) }
            )
            Spacer(modifier = Modifier.height(App.dimens.viewSpacingSmall))
            val focusManager = LocalFocusManager.current
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = longitude?.toString().orEmpty(),
                onValueChange = setLongitude,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        add()
                        focusManager.clearFocus()
                    }
                ),
                label = { Text(text = stringResource(id = R.string.locations_add_longitude)) }
            )
            Spacer(modifier = Modifier.height(App.dimens.viewSpacingMedium))
            addError?.let { error ->
                Text(text = stringResource(id = error), color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(App.dimens.viewSpacingSmall))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    enabled = !locationLoading,
                    onClick = {
                        if (hasLocationPermission) {
                            setLocation()
                        } else {
                            preciseLocationLauncher.launch(
                                arrayOf(
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                )
                            )
                        }
                    }
                ) {
                    Crossfade(targetState = locationLoading, label = "loadingLocation") { loading ->
                        if (loading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(App.dimens.buttonLoadingIndicatorSize)
                                    .align(Alignment.CenterVertically)
                            )
                        } else {
                            Text(text = stringResource(id = R.string.locations_get))
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1F))
                Button(
                    onClick = {
                        add()
                        focusManager.clearFocus()
                    },
                ) {
                    Text(text = stringResource(id = R.string.locations_add))
                }
            }
        }
    }
}
