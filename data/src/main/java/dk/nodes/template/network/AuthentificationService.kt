package dk.nodes.template.network

import android.telecom.Call
import dk.nodes.template.models.AccessToken
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthentificationService {
    @Headers("Content-Type: application/x-www-form-urlencoded"
    )

    @FormUrlEncoded
    @POST("/connect/token")
    fun getTestMessage(@Field("client_id") client_id: String, @Field("grant_type") grant_type: String, @Field("refresh_token") refresh_token: String): retrofit2.Call<AccessToken>
}