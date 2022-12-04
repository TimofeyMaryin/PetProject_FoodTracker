package android.realproject.trackerfood.data.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class SelectContentForBgViewModel(
    private val navController: NavController,
    private val mainViewModel: MainViewModel,
    private val context: Context
): ViewModel() {

    val listColorBg = listOf(
        Color(0xFF485461),
        Color(0xFF233329),
        Color (0xFF414141),
        Color(0xFF5B6467),
        Color(0xFF8B939A),
        Color(0xFFFFA69E),
        Color(0xFF5D4954),
        Color(0xFF166D3B),
        Color(0xFF04619F),
        Color(0xFF923CB5),
        Color(0xFF2C3E50),
        Color(0xFFA55C1B),
        Color(0xFFB82E1F),
        Color(0xFFE84393)
    )


    val listImageUrl = listOf(
        "https://i.pinimg.com/236x/10/74/dd/1074ddfefa27eebeabe1c86d4cb83165.jpg",
        "https://i.pinimg.com/236x/3d/4c/ef/3d4cef84a43ef5a427218929daa67436.jpg",
        "https://i.pinimg.com/236x/25/f4/93/25f4933a74a177d8db32719e7c932e69.jpg",
        "https://i.pinimg.com/736x/4f/94/d0/4f94d0a6797f8202f707e1e07a80a1af.jpg",
        "https://i.pinimg.com/236x/bb/6b/c3/bb6bc342564a252c0f6a352b5c87cabe.jpg",
        "https://i.pinimg.com/236x/75/86/66/758666c08b17c84b4eddd184d04f4a93.jpg",
        "https://i.pinimg.com/236x/47/f0/42/47f042e9843e507e1d9078e161764b59.jpg",
        "https://i.pinimg.com/236x/1d/fe/69/1dfe69dba64ce1ea13005bdc8d2b82e3.jpg",
        "https://i.pinimg.com/236x/47/e3/2a/47e32a172fd9c83f6fb862da33189377.jpg",
        "https://i.pinimg.com/236x/be/0a/cc/be0acc12b25671c5e27b31380e69816c.jpg",
        "https://i.pinimg.com/236x/32/d1/a6/32d1a6a1a056dd5f0d743044f38d2352.jpg",
        "https://i.pinimg.com/236x/32/d1/a6/32d1a6a1a056dd5f0d743044f38d2352.jpg",
        "https://i.pinimg.com/236x/89/d6/51/89d651fd5996fd063e634196d1519964.jpg",
        "https://i.pinimg.com/236x/f8/13/89/f813897b8a8b736c8f7caa308534aed2.jpg",
        "https://i.pinimg.com/236x/b0/62/0c/b0620c163b02eadbcbb6f7aad4ce95a0.jpg"
    )

    fun selectColorBg(color: Color) {
        navController.popBackStack()
        Toast.makeText(context, "Color change", Toast.LENGTH_SHORT).show()
    }

    fun selectImageBg(url: String) {
        navController.popBackStack()
        Toast.makeText(context, "Image change", Toast.LENGTH_SHORT).show()
    }

}