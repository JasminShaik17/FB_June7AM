package cubex.mahesh.fb_june7am

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.R.attr.bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector





class MLActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ml)

        var bmp = BitmapFactory.decodeResource(
                resources,R.drawable.slide5new)

        val image = FirebaseVisionImage.fromBitmap(bmp)
        val detector = FirebaseVision.getInstance()
                .visionTextDetector
        detector.detectInImage(image)
                .addOnSuccessListener {
Toast.makeText(this@MLActivity,
        "Sucess",Toast.LENGTH_LONG
        ).show()
                }
                .addOnFailureListener({
                    Toast.makeText(this@MLActivity,
                            "Fail",Toast.LENGTH_LONG
                    ).show()
                })
    }
}
