package cubex.mahesh.fb_june7am

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_profile_pic.*
import com.google.firebase.storage.StorageReference



class ProfilePicActivity : AppCompatActivity() {

    private var mStorageRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_pic)

        mStorageRef = FirebaseStorage.getInstance().getReference("/images");

        cam.setOnClickListener({

            var i = Intent("android.media.action.IMAGE_CAPTURE")
            startActivityForResult(i,123)

        })
        gal.setOnClickListener({
            var i = Intent( )
            i.setAction(Intent.ACTION_GET_CONTENT)
            i.setType("image/*")
            startActivityForResult(i,124)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if(requestCode==123 && resultCode== Activity.RESULT_OK)
            {
            var bmp =  data!!.extras.get("data") as Bitmap
            profile_pic_iview.setImageBitmap(bmp)
            }else if(requestCode==124 && resultCode== Activity.RESULT_OK)
            {
                    var u:Uri = data!!.data
                profile_pic_iview.setImageURI(u)

                mStorageRef!!.child(
                FirebaseAuth.getInstance().currentUser!!.uid+"/profile_pic.jpg")
                mStorageRef!!.putFile(u).addOnCompleteListener({

                    if(it.isSuccessful){
Toast.makeText(this@ProfilePicActivity,
        "Uploaded Successfully",
        Toast.LENGTH_LONG).show()

     var url =    it.result.downloadUrl .toString()
                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("/users/"+
                                FirebaseAuth.getInstance().uid)
                        myRef.child("profile_pic").setValue(url)
                    }else{
                        Toast.makeText(this@ProfilePicActivity,
                                "Failed to  Upload...",
                                Toast.LENGTH_LONG).show()
                    }

                })
            }
    }



} // ProfilePicActivity
