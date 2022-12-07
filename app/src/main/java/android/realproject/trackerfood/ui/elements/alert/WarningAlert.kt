package android.realproject.trackerfood.ui.elements.alert

import android.graphics.Paint.Style
import android.realproject.trackerfood.data.viewModel.SettingViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontSynthesis.Companion.Style
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WarningAlert(
    settingViewModel: SettingViewModel
) {


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(text = "Предупреждение!!".uppercase(), style = MaterialTheme.typography.h6)
        }
        item {
            Text(
                text = "Следующий фрагмент являеться воплощением некомпетентности разработчика сделать нормальное приложение. Следующий пункты, которые вы увидите не будут сохранены в ПЗУ и => при повторном открытие приложения все настройки слетят к чертовой матери!  Предупрежден, значит вооружен. Удачи!",
                style = TextStyle(
                    fontSize =18.sp,
                    letterSpacing = 1.sp,
                    lineHeight = 21.sp,
                    color = Color.White
                ),
                color = Color.White,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(vertical = 10.dp)
            )

        }
        item {
            if (!settingViewModel.userAgree) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Я соглашаюсь на этот развод", color = Color.White)
                    Checkbox(
                        checked = settingViewModel.userAgree,
                        onCheckedChange = {settingViewModel.userAgree = !settingViewModel.userAgree }
                    )
                }
            } else {
                Button(
                    onClick = { settingViewModel.userAgreeWarning = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Начать",
                        style = MaterialTheme.typography.h6,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }

    }
}