package com.example.snackordering

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snackordering.ui.theme.SnackOrderingTheme

class MainPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackOrderingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Render the final view
                    FinalView(context = this)
                }
            }
        }
    }
}

@Composable
fun FinalView(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopPart()
        Spacer(modifier = Modifier.height(16.dp))
        CardPart()
        Spacer(modifier = Modifier.height(16.dp))
        PopularFoodColumn(context)
    }
}

@Composable
fun TopPart() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffeceef0)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Menu Icon",
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp),
            tint = Color.Black
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Location", style = MaterialTheme.typography.subtitle1, color = Color.Black)
            Row {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = Color.Red
                )
                Text(text = "Accra", color = Color.Black)
            }
        }
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notification Icon",
            modifier = Modifier.size(45.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun CardPart() {
    Card(
        modifier = Modifier
            .size(width = 310.dp, height = 150.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(text = "Get Special Discounts")
                Text(
                    text = "up to 85%",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                    Text(text = "Claim voucher", color = MaterialTheme.colors.surface)
                }
            }
            Image(
                painter = painterResource(id = R.drawable.food_tip_im),
                contentDescription = "Food Image",
                modifier = Modifier.size(width = 100.dp, height = 200.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun PopularFoodColumn(context: Context) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(listOf(
            Pair(R.drawable.sandwich, R.string.sandwich),
            Pair(R.drawable.burgers, R.string.burgers)
        )) { item ->
            PopularFood(drawable = item.first, text1 = item.second, context = context)
        }
    }
}

@Composable
fun PopularFood(
    @DrawableRes drawable: Int,
    @StringRes text1: Int,
    context: Context
) {
    Card(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 20.dp, start = 65.dp)
            .width(250.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star Icon",
                    tint = Color.Yellow
                )
                Text(text = "4.3", fontWeight = FontWeight.Black)
            }
            Image(
                painter = painterResource(id = drawable),
                contentDescription = "Food Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Text(text = stringResource(id = text1), fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$50",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                IconButton(onClick = {
                    val intent = Intent(context, MainPage::class.java) // Replace with actual target
                    context.startActivity(intent)
                }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Shopping cart"
                    )
                }
            }
        }
    }
}
