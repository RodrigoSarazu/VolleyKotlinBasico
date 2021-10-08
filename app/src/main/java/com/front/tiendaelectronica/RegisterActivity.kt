package com.front.tiendaelectronica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.RadioButton
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        regresar()

        btnRegistrarse.setOnClickListener {
            if(etPasswordReg.text.toString().equals(etConfirmPasswordReg.text.toString())){
                var url="http://192.168.1.9/Ecommerce/UserController/RegisterUser.php?nombre="+etNombreReg.text.toString()+
                        "&apellidos="+etApellidoReg.text.toString()+"&email="+etEmailReg.text.toString()+
                        "&password="+etPasswordReg.text.toString()+"&celular="+etCelularReg.text.toString()+
                        "&dni="+etDniReg.text.toString()

                var rq:RequestQueue=Volley.newRequestQueue(this)
                var sr=StringRequest(Request.Method.GET,url,{
                    response->
                    if(response.equals("invalido")){
                        Toast.makeText(this,"Ya esta registrado",Toast.LENGTH_SHORT).show()
                    }else{
                        var i=Intent(this,LoginActivity::class.java)
                        startActivity(i)
                        Toast.makeText(this,"Se registro Correctamente",Toast.LENGTH_SHORT).show()
                    }
                },{ error->
                    Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
                })
                rq.add(sr)
            }else{
                Toast.makeText(this,"No coincide la contraseña",Toast.LENGTH_SHORT).show()
                etPasswordReg.clearFocus()
                etConfirmPasswordReg.clearFocus()
            }
        }
        }
    fun regresar(){
        btnRegresar.setOnClickListener {
            var i=Intent(this,LoginActivity::class.java)
            startActivity(i)
        }
    }
}