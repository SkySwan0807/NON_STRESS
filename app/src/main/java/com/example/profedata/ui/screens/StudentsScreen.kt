package com.example.profedata.ui.screens

import androidx.compose.ui.tooling.preview.Preview
import com.example.profedata.ui.theme.ProfeDataTheme // Asegúrate de que el nombre coincida con tu tema

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.profedata.ui.viewmodels.StudentsViewModel

// Modelo de datos (Asegúrate de que coincida con el del ViewModel)
data class Student(val id: Int, val name: String, val grade: String)

@Composable
fun StudentsScreen(
    viewModel: StudentsViewModel = hiltViewModel()
) {
    // Observamos la lista desde el ViewModel
    val students by viewModel.students.collectAsState()

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFF5F5F5))
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // FONDO DECORATIVO
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(brush = backgroundGradient)
            drawCircle(
                color = Color(0xFFE3F2FD), // Azul pastel
                radius = size.width * 0.6f,
                center = Offset(x = size.width * 0.9f, y = size.height * 0.1f)
            )
        }

        // CONTENIDO
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Estudiantes",
                color = Color(0xFF212121),
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "${students.size} alumnos registrados",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(students) { student ->
                    StudentItem(student)
                }
            }
        }
    }
}

@Composable
fun StudentItem(student: Student) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = Color(0xFF2196F3)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = student.name.take(1),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = student.name,
                    color = Color(0xFF212121),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    text = student.grade,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudentsScreenPreview() {
    ProfeDataTheme {
        StudentsScreen()
    }
}