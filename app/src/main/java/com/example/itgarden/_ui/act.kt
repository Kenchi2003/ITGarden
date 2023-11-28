package com.example.itgarden._ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.itgarden.R
import com.example.itgarden.ui.theme.ITGardenTheme


@Composable
fun ActMenuIcon(modifier: Modifier=Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(text = stringResource(id = R.string.Head1),
//            style = TextStyle(
//                fontSize = 24.sp,
//                shadow = Shadow(Color(0xFF000000))
//            ),
//            modifier = Modifier.padding(start = 10.dp)
//        )
        Row {
            MenuIcon(stringResource(R.string.icon_menu1),
                R.drawable.icon_menu1,
                onClick = { Log.d("user442","succ")})
            MenuIcon(stringResource(R.string.menu2),R.drawable.menu2,onClick = {})
            MenuIcon(stringResource(R.string.menu3),R.drawable.menu3,onClick = {})
        }
    }
}
@Preview(showBackground = true)
@Composable
fun viewmenuicon() {
    ITGardenTheme {
        ActMenuIcon()
    }
}