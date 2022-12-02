package android.realproject.trackerfood.data.viewModel

import android.realproject.trackerfood.data.db.avatar_db.AvatarDaoImpl
import android.realproject.trackerfood.data.db.avatar_db.AvatarEntity
import android.realproject.trackerfood.model.AvatarModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SelectImageViewModel(
    private val mainViewModel: MainViewModel
):ViewModel(), AvatarDaoImpl {
    val imageUrl = mutableListOf(
        mutableStateOf( AvatarModel("https://i.pinimg.com/236x/59/6c/33/596c33eebd7a5d213541fcd56d88440d.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/7c/6d/85/7c6d8558ad8abb16cb651892c6f090bd.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/ab/85/f5/ab85f5c5114c2632f6fb11887cf43c37.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/a3/b9/52/a3b952d943dffc98eff5f07101f7fe6c.jpg")),
        mutableStateOf(AvatarModel( "https://i.pinimg.com/236x/eb/bc/e5/ebbce574b429110031708bfd583f8292.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/eb/bc/e5/ebbce574b429110031708bfd583f8292.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/73/64/51/736451a9a66007119c175950d988610d.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/89/06/07/8906077c66a75e2226084253680ceeb0.jpg")),
        mutableStateOf(AvatarModel( "https://i.pinimg.com/236x/7c/c4/45/7cc4458c3e109bfb2fe38174611a7ee1.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/1a/22/30/1a2230c5d1d3770b04b3ef64efa5eba3.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/d8/90/7d/d8907d115b260b0f8fab99e1ff87e81a.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/c5/a6/4a/c5a64a70802baa153ead913c687b8791.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/6c/4b/3d/6c4b3d4390a63c28a9048caea216ccb1.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/1e/62/3f/1e623f17d51576854c16b5f949437b9c.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/736x/47/64/6d/47646d280b264c572cba58141c05d104.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/236x/de/62/c0/de62c0ee76baa47852d3fcac9deb2dc0.jpg")),
        mutableStateOf(AvatarModel("https://i.pinimg.com/736x/0b/6c/18/0b6c18ab3bdf1e9c1e4f209a0f0ff7e8.jpg"))
    )
    private val defaultImage by mutableStateOf(
        "https://i.pinimg.com/564x/28/86/8c/28868c4b45558019fa6e3bafd0fc4c1f.jpg"
    )


    var selectedImage by mutableStateOf(defaultImage)
        private set
    fun selectImage(index: Int) {
        selectedImage = imageUrl[index].value.url
    }

    private fun alreadySelected(): Pair<Boolean, Int> {
        var isSelected by mutableStateOf(false)
        var index by mutableStateOf(0)

        viewModelScope.launch {
            for (i in imageUrl.indices) {
                isSelected = imageUrl[i].value.selected
                if (isSelected) {
                    index = i
                    return@launch
                }
            }


        }
        if (isSelected) {
            return Pair(imageUrl[index].value.selected, index)
        }
        return Pair(false, -1)

    }

    fun isSelectAvatar() = alreadySelected().first


    override fun deleteAvatar(avatarEntity: AvatarEntity) = mainViewModel.deleteAvatar(avatarEntity)
    override fun insertAvatar(avatarModel: AvatarEntity) = mainViewModel.insertAvatar(avatarModel)
    override fun deleteAllAvatar() = mainViewModel.deleteAllAvatar()


}