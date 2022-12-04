package android.realproject.trackerfood.ui.fragments

import android.realproject.trackerfood.ui.elements.SelectBgContent
import android.realproject.trackerfood.ui.elements.TopBarSelectFragment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

@Composable
fun SelectBgFragment(
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (topBar, content) = createRefs()

        TopBarSelectFragment(
            modifier = Modifier.constrainAs(topBar){
                                                   top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            navController = navController
        )

        SelectBgContent(modifier = Modifier.constrainAs(content) {
            top.linkTo(topBar.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, navController)
    }
}