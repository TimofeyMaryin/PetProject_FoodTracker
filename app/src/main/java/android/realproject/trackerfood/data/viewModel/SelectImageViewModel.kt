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
        "https://i.pinimg.com/236x/23/06/dc/2306dc7a5feb31e9fdcd358758f73c7e.jpg",
        "https://i.pinimg.com/236x/c5/70/78/c5707894eade9e74b926b56600aa0d56.jpg",
        "https://i.pinimg.com/236x/59/6c/33/596c33eebd7a5d213541fcd56d88440d.jpg",
        "https://i.pinimg.com/236x/7c/6d/85/7c6d8558ad8abb16cb651892c6f090bd.jpg",
        "https://i.pinimg.com/236x/ab/85/f5/ab85f5c5114c2632f6fb11887cf43c37.jpg",
        "https://i.pinimg.com/236x/eb/bc/e5/ebbce574b429110031708bfd583f8292.jpg",
        "https://i.pinimg.com/236x/73/64/51/736451a9a66007119c175950d988610d.jpg",
        "https://i.pinimg.com/236x/89/06/07/8906077c66a75e2226084253680ceeb0.jpg",
        "https://i.pinimg.com/236x/7c/c4/45/7cc4458c3e109bfb2fe38174611a7ee1.jpg",
        "https://i.pinimg.com/236x/1a/22/30/1a2230c5d1d3770b04b3ef64efa5eba3.jpg",
        "https://i.pinimg.com/236x/d8/90/7d/d8907d115b260b0f8fab99e1ff87e81a.jpg",
        "https://i.pinimg.com/236x/c5/a6/4a/c5a64a70802baa153ead913c687b8791.jpg",
        "https://i.pinimg.com/236x/6c/4b/3d/6c4b3d4390a63c28a9048caea216ccb1.jpg",
        "https://i.pinimg.com/236x/1e/62/3f/1e623f17d51576854c16b5f949437b9c.jpg",
        "https://i.pinimg.com/736x/47/64/6d/47646d280b264c572cba58141c05d104.jpg",
        "https://i.pinimg.com/236x/de/62/c0/de62c0ee76baa47852d3fcac9deb2dc0.jpg",
        "https://i.pinimg.com/736x/0b/6c/18/0b6c18ab3bdf1e9c1e4f209a0f0ff7e8.jpg",
        "https://sun3-11.userapi.com/impg/3ZCdTo-hDwSgEOliaazpmmd8Z_SQeuah5ujXvQ/JAekgMWGIWo.jpg?size=1831x1832&quality=96&sign=d15af942884071e525d8981cb8450a4e&type=album",
        "https://i.pinimg.com/236x/d6/0a/1b/d60a1b5e4cca49a706d250cc7b3136c8.jpg",
        "https://i.pinimg.com/236x/30/78/49/3078491426e9171b385527eb1758bbd1.jpg",
        "https://i.pinimg.com/236x/fa/69/46/fa694651da1d99784706213db7a12a41.jpg",
        "https://i.pinimg.com/236x/e4/e2/66/e4e2668df270eaf7018a8776f6868121.jpg",
        "https://i.pinimg.com/236x/83/8b/78/838b78246a9ccea69c5561c0ecebf8e7.jpg",
        "https://i.pinimg.com/236x/cb/3e/b9/cb3eb9792651488e9bba1433094d59be.jpg",
        "https://i.pinimg.com/236x/d1/26/f7/d126f7b03b054af53a85b78857863f02.jpg",
        "https://i.pinimg.com/236x/de/23/f3/de23f34f13f638534dbded09db5c9206.jpg",
        "https://i.pinimg.com/236x/79/52/4a/79524a97f0aefb7e83891fa3fd37d0f5.jpg",
        "https://sun3-16.userapi.com/impg/512XTcmJgKvxUnuDtDr_qLpHVw5poVWoiCE-4w/5LbQRFoULC8.jpg?size=828x1472&quality=95&sign=3b30a6194801704b145235bf4b95948a&type=album",
        "https://i.pinimg.com/236x/f3/1d/b5/f31db5181042a448b475a6ef18b36675.jpg",
        "https://i.pinimg.com/236x/f0/67/3d/f0673d682ad74b22f9e5a4fe5b2e13ea.jpg",
        "https://i.pinimg.com/236x/d6/7b/c3/d67bc353218e258e27740fda32e0352d.jpg",
        "https://i.pinimg.com/236x/48/b4/4a/48b44ac8432469edb876d6e36c8a2f30.jpg",
        "https://i.pinimg.com/236x/48/b4/4a/48b44ac8432469edb876d6e36c8a2f30.jpg",
        "https://i.pinimg.com/236x/01/34/98/0134984bc33a7b74f798435d630c082d.jpg",
        "https://sun9-77.userapi.com/impg/XB2hP8Ss_AN9ShWZBzAafh8j-P7J8DcWzRfAGQ/qwHL4bxLhn0.jpg?size=1080x1080&quality=96&sign=625449da7e65cf8c05b54ee77f0a5f6b&type=album"
    )
    private val defaultImage by mutableStateOf(
        "https://i.pinimg.com/564x/28/86/8c/28868c4b45558019fa6e3bafd0fc4c1f.jpg"
    )


    var selectedImage by mutableStateOf(defaultImage)
        private set
    fun selectImage(index: Int) {
        selectedImage = imageUrl[index]
    }




    override fun deleteAvatar(avatarEntity: AvatarEntity) = mainViewModel.deleteAvatar(avatarEntity)
    override fun insertAvatar(avatarModel: AvatarEntity) = mainViewModel.insertAvatar(avatarModel)
    override fun deleteAllAvatar() = mainViewModel.deleteAllAvatar()


}