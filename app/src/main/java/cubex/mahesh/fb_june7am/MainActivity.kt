package cubex.mahesh.fb_june7am

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance();

        var mAdView:AdView = findViewById<View>(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
    fun login(v:View){
        mAuth!!.signInWithEmailAndPassword(
                et1!!.text.toString(), et2!!.text.toString()
        ).addOnCompleteListener({
            if(it.isSuccessful){
                startActivity(Intent(this@MainActivity,
                        DashboardActivity::class.java))
            }else{
                Toast.makeText(this@MainActivity,
                        "Please provide valid Auth Credentials",
                        Toast.LENGTH_LONG).show()
            }
        })
    }

    fun register(v:View){
        mAuth!!.createUserWithEmailAndPassword(
                et1!!.text.toString(), et2!!.text.toString()
        ).addOnCompleteListener({
            if(it.isSuccessful){
                startActivity(Intent(this@MainActivity,
                        DatabaseActivity::class.java))
            }else{
                Toast.makeText(this@MainActivity,
                        "Please provide valid Auth Credentials",
                        Toast.LENGTH_LONG).show()
            }
        })
    }

    fun  mlkit(v:View)
    {
        startActivity(Intent(this@MainActivity,
                MLActivity::class.java))
    }
}
