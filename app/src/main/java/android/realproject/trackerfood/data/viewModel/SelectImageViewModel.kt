package android.realproject.trackerfood.data.viewModel

import android.realproject.trackerfood.data.db.avatar_db.AvatarDaoImpl
import android.realproject.trackerfood.data.db.avatar_db.AvatarEntity
import android.realproject.trackerfood.model.AvatarModel
import android.realproject.trackerfood.utils.DEFAULT_IMAGE
import android.realproject.trackerfood.utils.LIST_OF_AVATAR
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SelectImageViewModel(
    private val mainViewModel: MainViewModel
):ViewModel(), AvatarDaoImpl {

    var selectedImage by mutableStateOf(DEFAULT_IMAGE)
        private set
    fun selectImage(index: Int) { selectedImage = LIST_OF_AVATAR[index] }
    override fun deleteAvatar(avatarEntity: AvatarEntity) = mainViewModel.deleteAvatar(avatarEntity)
    override fun insertAvatar(avatarModel: AvatarEntity) = mainViewModel.insertAvatar(avatarModel)
    override fun deleteAllAvatar() = mainViewModel.deleteAllAvatar()


}