package com.front.tiendaelectronica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnRegister.setOnClickListener {
            registro()
        }
        btnLogin.setOnClickListener {
            login()
        }

    }
    fun login(){

        var url="http://192.168.1.9/Ecommerce/UserController/loginUser.php?email="+etEmailLogin.text.toString()+
                "&password="+etPasswordLogin.text.toString()
        var rq: RequestQueue= Volley.newRequestQueue(this)
        var jar=JsonObjectRequest(Request.Method.POST, url, null, { response ->
            //recuerda que para traer un JSON es obligatoria traer igualmente ya sea si tiene un "-" o sea "En Mayuscula"
            var s=response.getInt("IdUsu")
            //Almacenando el Idusu en UserInfo
            UserInfo.idusu=s
            Log.d("respuesta", s.toString())
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }, { error ->
            Toast.makeText(this, "Intende de nuevo por favor", Toast.LENGTH_SHORT).show()
        })
        rq.add(jar)
    }

    fun registro(){
        var i=Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }
}