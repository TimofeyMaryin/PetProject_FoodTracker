package android.realproject.trackerfood.ui.fragments

import android.realproject.trackerfood.data.viewModel.SelectImageViewModel
import android.realproject.trackerfood.ui.elements.AppTopBar
import android.realproject.trackerfood.ui.elements.AvatarGridList
import android.realproject.trackerfood.ui.elements.FAB
import android.realproject.trackerfood.ui.elements.TopBarPreviewAvatar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp

@Composable
fun SelectAvatarFragment(
    navController: NavController,
    viewModel: SelectImageViewModel
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (confirmAvatar, avatarList, topBar) = createRefs()

        TopBarPreviewAvatar(
            modifier = Modifier.constrainAs(topBar) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            viewModel = viewModel,
            navController = navController
        )
        AvatarGridList(
            viewModel = viewModel,
            modifier = Modifier.constrainAs(avatarList) {
                top.linkTo(topBar.bottom, margin = 10.dp)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        )
        if (viewModel.isSelectAvatar()) {
            FAB(
                modifier = Modifier.constrainAs(confirmAvatar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                },
                onClickAction = {
                    navController.popBackStack()
                },
                icon = Icons.Default.Done
            )
        }

    }
}