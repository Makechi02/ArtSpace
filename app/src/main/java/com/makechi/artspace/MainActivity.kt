package com.makechi.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makechi.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {

    var imageIndex by remember { mutableIntStateOf(1) }

    val artId = when(imageIndex) {
        1 -> R.drawable.art_1
        2 -> R.drawable.art_2
        3 -> R.drawable.art_3
        4 -> R.drawable.art_4
        5 -> R.drawable.art_5
        else -> R.drawable.art_5
    }

    val artTitle = when(imageIndex) {
        1 -> stringResource(id = R.string.title_art_1)
        2 -> stringResource(id = R.string.title_art_2)
        3 -> stringResource(id = R.string.title_art_3)
        4 -> stringResource(id = R.string.title_art_4)
        5 -> stringResource(id = R.string.title_art_5)
        else -> "Unknown"
    }

    val artAuthor = when(imageIndex) {
        1 -> stringResource(id = R.string.author_art_1)
        2 -> stringResource(id = R.string.author_art_2)
        3 -> stringResource(id = R.string.author_art_3)
        4 -> stringResource(id = R.string.author_art_4)
        5 -> stringResource(id = R.string.author_art_5)
        else -> "Unknown"
    }

    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(16.dp)
            .safeDrawingPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtWorkWall(
            artId = artId,
            artDescription = artTitle,
            modifier = Modifier.weight(weight = 1F)
        )

        ArtWorkDescriptor(
            artTitle = artTitle,
            artAuthor = artAuthor
        )

        DisplayController(
            onPreviousClick = { if (imageIndex == 1) imageIndex = 1 else imageIndex-- },
            onNextClick = { if (imageIndex == 5) imageIndex = 5 else imageIndex++ }
        )
    }
}

@Composable
fun ArtWorkWall(
    artId: Int,
    artDescription: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 40.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.clip(RoundedCornerShape(12.dp))) {
            Image(
                painter = painterResource(id = artId),
                contentDescription = artDescription,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ArtWorkDescriptor(
    artTitle: String,
    artAuthor: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = artTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(text = artAuthor, textAlign = TextAlign.Center)
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (modifier = modifier.padding(vertical = 16.dp)) {
        ControllerButton(
            text = stringResource(id = R.string.previous),
            clickHandler = onPreviousClick,
            modifier = Modifier.weight(weight = 1F)
        )

        Spacer(modifier = Modifier.width(width = 16.dp))

        ControllerButton(
            text = stringResource(id = R.string.next),
            clickHandler = onNextClick,
            modifier = Modifier.weight(weight = 1F)
        )
    }
}

@Composable
fun ControllerButton(
    text: String,
    clickHandler: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = clickHandler,
        modifier = modifier
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}