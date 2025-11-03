package com.example.studentprofileapp.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studentprofileapp.R
import com.example.studentprofileapp.model.Student

@Composable
fun StudentListScreen(
    students: List<Student>,
    onStudentClick: (Int) -> Unit
) {

    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
        )
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        color = MaterialTheme.colorScheme.background.copy(alpha = 0f)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 24.dp)
        ) {
            items(students) { student ->
                StudentListItem(student = student) {
                    onStudentClick(student.id)
                }
            }
        }
    }
}

@Composable
private fun StudentListItem(student: Student, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imageRes = getDrawableIdByName(student.photoResName)
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = student.fullName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = student.major,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

private fun getDrawableIdByName(name: String): Int {
    return try {
        val field = R.drawable::class.java.getDeclaredField(name)
        field.getInt(null)
    } catch (e: Exception) {
        R.drawable.avatar_default
    }
}
