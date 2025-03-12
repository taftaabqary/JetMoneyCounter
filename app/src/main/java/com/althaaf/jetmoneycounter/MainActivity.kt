package com.althaaf.jetmoneycounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.althaaf.jetmoneycounter.ui.theme.JetMoneyCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetMoneyCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val counterMoney = remember {
        mutableIntStateOf(0)
    }

    Surface(modifier = modifier.fillMaxSize(), color = Color.Cyan) {
        Column(modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Total : $${counterMoney.intValue}", modifier = Modifier.padding(bottom = 56.dp),
                style = MaterialTheme.typography.headlineMedium)
            CreateCircle(moneyCount = counterMoney.intValue) { count ->
                counterMoney.intValue = count
            }
            if(counterMoney.intValue > 25) {
                Text("You're so rich bro...!")
            }
        }
    }
}

@Composable
fun CreateCircle(modifier: Modifier = Modifier, moneyCount: Int, onTapMoney: (Int) -> Unit) {
    var count = moneyCount
    Card(
        modifier = modifier.padding(4.dp).size(120.dp)
            .clickable {
                count++
                onTapMoney(count)
            },
        shape = CircleShape,
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Tap : $moneyCount", modifier = Modifier, color = Color.Black, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
        }
    }
}
