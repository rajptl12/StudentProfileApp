package com.example.studentprofileapp.ui.theme.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studentprofileapp.R
import com.example.studentprofileapp.model.Student
import androidx.compose.foundation.clickable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDetailScreen(student: Student?, onBack: () -> Unit) {
    val context = LocalContext.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFEBEE),
                        Color(0xFFFFCDD2)
                    )
                )
            )
    ) {
        Column {

            TopAppBar(
                title = { Text("Student Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )

            if (student == null) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Student not found.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {

                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { visible = true }

                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it / 3 })
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        var elevated by remember { mutableStateOf(false) }
                        val elevation by animateDpAsState(if (elevated) 12.dp else 6.dp)

                        Card(
                            shape = RoundedCornerShape(20.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = elevation),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable { elevated = !elevated },
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val imageRes = getDrawableIdByName(context, student.photoResName)
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = "Student photo",
                                    modifier = Modifier
                                        .size(140.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Fit
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = student.fullName,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFB71C1C) // Montclair red
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = "Major: ${student.major}",
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = student.shortBio,
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                Button(onClick = onBack) {
                                    Text("Back")
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))


                        Text(
                            text = "Montclair State University",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

/**
 * Safely get drawable resource ID by name.
 * This version will never crash even if the name is invalid.
 */
fun getDrawableIdByName(context: Context, name: String): Int {
    val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
    return if (resId != 0) resId else R.drawable.avatar_default
}
