import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.R
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeApp()
        }
    }
}

@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var pressCount by remember { mutableStateOf(0) }
    var pressesLeft by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (currentStep) {
            1 -> LemonTree {
                currentStep = 2
                pressCount = Random.nextInt(2, 5)
                pressesLeft = pressCount
            }
            2 -> Lemon(pressesLeft) {
                pressesLeft--
                if (pressesLeft <= 0) {
                    currentStep = 3
                }
            }
            3 -> Lemonade {
                currentStep = 4
            }
            4 -> EmptyGlass {
                currentStep = 1
            }
        }
    }
}

@Composable
fun LemonTree(onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.tap_tree),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.lemon_tree),
            contentDescription = stringResource(id = R.string.content_tree),
            modifier = Modifier
                .size(250.dp) 
                .clickable { onClick() }
        )
    }
}

@Composable
fun Lemon(pressesLeft: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.tap_lemon) + " ($pressesLeft presses left)",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.lemon_squeeze),
            contentDescription = stringResource(id = R.string.content_lemon),
            modifier = Modifier
                .size(250.dp)
                .clickable { onClick() }
        )
    }
}

@Composable
fun Lemonade(onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.tap_lemonade),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.lemon_drink),
            contentDescription = stringResource(id = R.string.content_lemonade),
            modifier = Modifier
                .size(250.dp)
                .clickable { onClick() }
        )
    }
}

@Composable
fun EmptyGlass(onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.tap_empty_glass),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.lemon_restart),
            contentDescription = stringResource(id = R.string.content_empty_glass),
            modifier = Modifier
                .size(250.dp)
                .clickable { onClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeApp()
}

@Preview(showBackground = true)
@Composable
fun LemonTreePreview() {
    LemonTree(onClick = {})
}

@Preview(showBackground = true)
@Composable
fun LemonPreview() {
    Lemon(pressesLeft = 3, onClick = {})
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    Lemonade(onClick = {})
}

@Preview(showBackground = true)
@Composable
fun EmptyGlassPreview() {
    EmptyGlass(onClick = {})
}
