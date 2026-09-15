package com.waxd.messaging.ui.conversation.mediapicker.camera

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.LifecycleOwner
import com.waxd.messaging.R
import com.waxd.messaging.util.UiUtils

@Composable
internal fun BindConversationCameraLifecycleEffect(
    cameraController: ConversationCameraController,
    cameraPermissionGranted: Boolean,
    isCameraPreviewVisible: Boolean,
    lifecycleOwner: LifecycleOwner,
) {
    DisposableEffect(
        cameraController,
        cameraPermissionGranted,
        isCameraPreviewVisible,
        lifecycleOwner,
    ) {
        when {
            cameraPermissionGranted && isCameraPreviewVisible -> {
                cameraController.bindToLifecycle(
                    lifecycleOwner = lifecycleOwner,
                    onError = {
                        UiUtils.showToastAtBottom(R.string.camera_error_opening)
                    },
                )
            }

            else -> cameraController.unbind()
        }

        onDispose {
            cameraController.unbind()
        }
    }
}
