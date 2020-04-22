package com.example.financas.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.financas.R
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*

const val BASE_URL = "http://10.0.2.2:3000"
const val ENDPOINT_USER = "/user"

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        buttonOpen.setOnClickListener(this)

    }

    override fun onClick(view: View) {

        if (view.id == R.id.buttonOpen) {
            handleName()
        }
    }

    private fun handleName() {

        val name: String = userName.text.toString()

        if (name == "") {
            Toast.makeText(this, "Por favor informe o seu nome!", Toast.LENGTH_LONG).show()
        } else {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val userService: UserService = retrofit.create(UserService::class.java)

            val headers = mutableMapOf<String, String>()
            headers["x-username"] = name
            userService.getUser(headers,"Vagner Martins", 26, "MASCULINO", true).enqueue(object : Callback<User> {

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("MyTag", "Error he he he he")
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val body = response.body()
                    Log.d("MyTag", "Hello World")
                }
            })

            userService.saveUser(User(name = "Vagner", age = 26, gender = "MASCULINO", username = "vagnnermartins")).enqueue(object : Callback<ResponseSaveUser>{
                override fun onFailure(call: Call<ResponseSaveUser>, t: Throwable) {
                    Log.d("", "")
                }

                override fun onResponse(call: Call<ResponseSaveUser>,response: Response<ResponseSaveUser>) {
                    Log.d("", "")
                }

            })

//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }
    }

    interface UserService {

        @GET(ENDPOINT_USER)
        fun getUser(
            @HeaderMap headers: Map<String, String>,
            @Query("name") name: String,
            @Query("age") age: Int,
            @Query("gender") gender: String,
            @Query("isVaccine") isVaccine: Boolean
        ): Call<User>

        @POST(ENDPOINT_USER)
        fun saveUser(@Body user: User): Call<ResponseSaveUser>

        @PUT("$ENDPOINT_USER/{userId}")
        fun updateUser(@Path("userId") userId: Long, @Body user: User): Call<ResponseUpdateUser>

        @DELETE("$ENDPOINT_USER/{userId}")
        fun deleteUser(@Path("userId") userId: Long): Call<ResponseDeleteUser>
    }

    data class User(
        val name: String?,
        val age: Int?,
        val gender: String?,
        val username: String?
    )

    data class ResponseSaveUser(val datetime: Date)

    data class ResponseUpdateUser(val datetime: Date)

    data class ResponseDeleteUser(val datetime: Date)
}
