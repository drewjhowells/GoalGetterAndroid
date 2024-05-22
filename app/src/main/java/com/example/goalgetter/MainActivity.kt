package com.example.goalgetter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goalgetter.ui.theme.GoalGetterTheme
import java.sql.Date

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			GoalGetterTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Main()
				}
			}
		}
	}
}

@Composable
fun Main() {
	var goals by remember {
		mutableStateOf(listOf("Goal 1"))
	}
	Scaffold (
		floatingActionButton = {
			FloatingActionButton(
				onClick = {
					if (goals.count() < 3) {
						goals += "Goal ${goals.count()+1}"
					}
				}
			) {
				Icon(imageVector = Icons.Default.Add, contentDescription = "New Goal")
			}
		}
	){ paddingValues ->
		LazyRow (
			modifier = Modifier.padding(paddingValues)
		){
			items(goals) {goal ->
				Box(
					modifier = Modifier
						.padding(5.dp)
						.width(100.dp)
						.fillMaxHeight()
						.clip(RoundedCornerShape(10.dp))
						.background(color = Color.Green)
						.clickable {
							// Do Something
						},
					contentAlignment = Alignment.Center
				) {
					Row {
						Text(
							text = goal,
							fontWeight = FontWeight.Bold,
							modifier = Modifier.weight(1f),
							textAlign = TextAlign.Center
						)
					}
				}
			}
		}
	}
}