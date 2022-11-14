package com.example.lemonade

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.lemonade.ui.theme.LemonadeTheme

var currentstep=1;

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    LemonTreeApp(name = "Akash")
                }
            }
        }
    }
}


@Composable
fun LemonTreeApp(name: String){
    var currentstep by remember{ mutableStateOf(1)}

    var squeezeCount by remember {
        mutableStateOf(0)
    }

    when(currentstep){
            1 ->Column(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = "Welcome $name")
                Button(onClick = {currentstep++}) {
                    Text(text = "Start")
                }

            }
            2 -> TextWithImage(
                textLblResId = R.string.lemon_select,
                drawableResId = R.drawable.lemon_tree,
                contentDescResId = R.string.lemon_select,
                onImageClick = {squeezeCount = (2..4).random()
                    currentstep = 3}
            )
            3 -> TextWithImage(
                    textLblResId = R.string.lemon_squeeze,
                    drawableResId = R.drawable.lemon_squeeze,
                    contentDescResId = R.string.lemon_squeeze,
                    onImageClick = { squeezeCount--
                        // When we're done squeezing the lemon, move to the next step
                        if (squeezeCount == 0) {
                            currentstep = 4}
                    }
                )
            4 -> TextWithImage(
                textLblResId = R.string.lemon_drink,
                drawableResId = R.drawable.lemon_drink,
                contentDescResId = R.string.lemon_drink,
                onImageClick = {currentstep=5}
                )
            5 -> TextWithImage(
                textLblResId = R.string.lemon_empty_glass,
                drawableResId = R.drawable.lemon_restart,
                contentDescResId = R.string.lemon_empty_glass,
                onImageClick = {currentstep=1}
                )
    }
}


@Composable
fun TextWithImage(
    textLblResId: Int,
    drawableResId: Int,
    contentDescResId: Int,
    onImageClick:() -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        val image = painterResource(drawableResId)

        Text(text = stringResource(textLblResId))

        Spacer(modifier = Modifier.
            height(16.dp))
        Image(painter = image,
            contentDescription = stringResource(contentDescResId),
            modifier = Modifier
                .clickable(
                    onClick = onImageClick)
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonTreeApp(name = "Akash")
    }
}