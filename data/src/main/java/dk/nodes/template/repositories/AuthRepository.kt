package dk.nodes.template.repositories

import android.content.SharedPreferences
import dk.nodes.template.models.Authentification
import dk.nodes.template.network.AuthentificationService
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authentificationService: AuthentificationService, private val sharedPreferences: SharedPreferences) {


    suspend fun fetch_Token(): String? {


        var accesinfo = Authentification("d2cc153a-3ad4-42b0-b832-43ee335e5ea5", "refresh_token", "Cd_XvxL-ukaX4wFejL1rjQ")

        val response = authentificationService.getTestMessage(accesinfo.client_id, accesinfo.grant_type, accesinfo.refresh_token).execute()

        if (response.isSuccessful) {

            val message = response.body()

            if (message != null) {

                message.message?.let {
                    sharedPreferences.edit().putString("access_token",  message.message).apply()

                    return  message.message

                }
            }
        }

        return null
    }




}