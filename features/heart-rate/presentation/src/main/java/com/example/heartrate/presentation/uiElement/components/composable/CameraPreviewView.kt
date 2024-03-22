package com.example.heartrate.presentation.uiElement.components.composable

import androidx.activity.ComponentActivity
import androidx.camera.core.Camera
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture


@Composable
fun CameraPreviewView(
    onImageAnalyzed: (ImageProxy) -> Unit,
    onCameraObjectDefined: (Camera) -> CameraControl,
    processCameraProvider: ListenableFuture<ProcessCameraProvider>?,
    modifier: Modifier = Modifier,
) {

    AndroidView(
        factory = { context ->
            // إنشاء عنصر واجهة مستخدم Android لعرض معاينة الكاميرا
            val previewView = PreviewView(context)
            // الحصول على Executor لتشغيل الكاميرا
            val cameraExecutor = ContextCompat.getMainExecutor(context)

            // إضافة مستمع لاستعداد مزود الكاميرا
            processCameraProvider?.addListener(
                {
                    // الحصول على مزود الكاميرا بعد الاستعداد
                    val cameraProvider = processCameraProvider.get()

                    // إعداد معاينة الكاميرا
                    val preview = Preview.Builder()
                        .build()

                    preview.setSurfaceProvider(previewView.surfaceProvider)

                    // اختيار الكاميرا الخلفية كافتراضي
                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    // إعداد تحليل الصورة لتحليل الصور الملتقطة من الكاميرا
                    val imageAnalysis = ImageAnalysis
                        .Builder()
                        .build()

                    // استدعاء دالة عند تحليل الصورة
                    imageAnalysis.setAnalyzer(cameraExecutor, onImageAnalyzed)


                    // ربط معاينة الكاميرا و ImageCapture وتحليل الصورة بحياة النشاط
                    val cameraControl = onCameraObjectDefined(
                        cameraProvider.bindToLifecycle(
                            (context as ComponentActivity),
                            cameraSelector,
                            preview,
                            imageAnalysis
                        )
                    )

                    cameraControl.enableTorch(true) // تمكين الفلاش

                },
                ContextCompat.getMainExecutor(context)
            )

            // إرجاع عنصر واجهة المستخدم لعرض معاينة الكاميرا
            previewView
        },
        modifier = modifier
    )
}//end CameraPreviewView