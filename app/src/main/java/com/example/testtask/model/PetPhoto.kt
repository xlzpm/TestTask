package com.example.testtask.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@Serializable
//data class PetPhotos(
//    val petPhotos: List<PetPhoto>
//){
//    fun getPhotos() {
//        return petPhotos.forEach { petPhoto ->
//            petPhoto.full
//        }
//    }
//}
//

@Keep
@Serializable
@SerialName("photos")
data class PetPhoto(
    @SerialName("full")
    val full: String
)
