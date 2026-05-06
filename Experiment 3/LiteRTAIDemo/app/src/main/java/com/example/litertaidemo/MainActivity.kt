package com.example.litertaidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.litertaidemo.ui.theme.LiteRTAIDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiteRTAIDemoTheme {
                LiteRtDemoScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiteRtDemoScreen() {
    var modelName by remember { mutableStateOf("MobileNet") }
    var resultText by remember { mutableStateOf("Cat") }
    var confidenceText by remember { mutableStateOf("96.2%") }
    var timeText by remember { mutableStateOf("28 ms") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "LiteRT AI Demo") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        color = Color(0xFFE5E5E5),
                        shape = RoundedCornerShape(12.dp)
                    ),
            ) {
                Text(
                    text = "Camera Preview",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Gray
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ResultLine(label = "Model", value = modelName)
                    ResultLine(label = "Result", value = resultText)
                    ResultLine(label = "Confidence", value = confidenceText)
                    ResultLine(label = "Time", value = timeText)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ActionButton(
                    modifier = Modifier.weight(1f),
                    text = "拍照识别",
                    containerColor = Color(0xFF1E88E5)
                )
                ActionButton(
                    modifier = Modifier.weight(1f),
                    text = "相册导入",
                    containerColor = Color(0xFF43A047)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ActionButton(
                    modifier = Modifier.weight(1f),
                    text = "切换模型",
                    containerColor = Color(0xFF5E35B1),
                    onClick = {
                        if (modelName == "MobileNet") {
                            modelName = "EfficientNet"
                            resultText = "Dog"
                            confidenceText = "91.8%"
                            timeText = "35 ms"
                        } else {
                            modelName = "MobileNet"
                            resultText = "Cat"
                            confidenceText = "96.2%"
                            timeText = "28 ms"
                        }
                    }
                )
                ActionButton(
                    modifier = Modifier.weight(1f),
                    text = "清空结果",
                    containerColor = Color(0xFFE53935),
                    onClick = {
                        resultText = "-"
                        confidenceText = "-"
                        timeText = "-"
                    }
                )
            }
        }
    }
}

@Composable
private fun ResultLine(label: String, value: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp / 2
        )
        Text(text = value, fontSize = 24.sp / 2)
    }
}

@Composable
private fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    containerColor: Color,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor)
    ) {
        Text(text = text, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun LiteRtDemoPreview() {
    LiteRTAIDemoTheme {
        LiteRtDemoScreen()
    }
}
