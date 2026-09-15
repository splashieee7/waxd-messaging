package com.waxd.messaging.ui.conversation.mediapicker.camera

import com.waxd.messaging.R
import com.waxd.messaging.data.media.model.ConversationCapturedMedia
import com.waxd.messaging.util.UiUtils

internal fun handlePhotoCaptureRequest(
    cameraController: ConversationCameraController,
    onAttachmentStartRequest: () -> Boolean,
    onCapturedMediaReady: (ConversationCapturedMedia) -> Unit,
    onShowReview: (String) -> Unit,
) {
    if (!onAttachmentStartRequest()) {
        return
    }

    cameraController.capturePhoto(
        onCaptured = { capturedMedia ->
            onCapturedMediaReady(capturedMedia)
            onShowReview(capturedMedia.contentUri)
        },
        onError = {
            UiUtils.showToastAtBottom(
                R.string.camera_error_failure_taking_picture,
            )
        },
    )
}

internal fun handleSwitchCameraRequest(cameraController: ConversationCameraController) {
    cameraController.switchCamera(
        onError = {
            UiUtils.showToastAtBottom(
                R.string.camera_error_opening,
            )
        },
    )
}

internal fun handleToggleFlashRequest(cameraController: ConversationCameraController) {
    cameraController.cyclePhotoFlashMode(
        onError = {
            UiUtils.showToastAtBottom(
                R.string.camera_error_opening,
            )
        },
    )
}

internal fun handleVideoCaptureRequest(
    cameraController: ConversationCameraController,
    isRecording: Boolean,
    onAttachmentStartRequest: () -> Boolean,
    onCapturedMediaReady: (ConversationCapturedMedia) -> Unit,
    onShowReview: (String) -> Unit,
) {
    if (isRecording) {
        cameraController.stopVideoRecording()
        return
    }

    if (!onAttachmentStartRequest()) {
        return
    }

    cameraController.startVideoRecording(
        withAudio = true,
        onCaptured = { capturedMedia ->
            onCapturedMediaReady(capturedMedia)
            onShowReview(capturedMedia.contentUri)
        },
        onDiscarded = {},
        onError = {
            UiUtils.showToastAtBottom(
                R.string.camera_media_failure,
            )
        },
    )
}
