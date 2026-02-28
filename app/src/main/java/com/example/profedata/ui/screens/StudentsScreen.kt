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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.sql.Date

// Modelo de datos para probar
data class Student(val id: Int, val nombre: String, val curso_id: String)

@Composable
fun StudentsScreen() {
    val students = listOf(
        Student(1, "Alex Johnson", "10th Grade"),
        Student(2, "Maria Garcia", "11th Grade" ),
        Student(3, "Samuel Smith", "10th Grade" ),
        Student(4, "Lucia Wong", "9th Grade" )
    )

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFF5F5F5))
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // FONDO (Canvas)
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(brush = backgroundGradient)
            drawCircle(
                color = Color(0xFFE3F2FD).copy(alpha = 0.2f),
                radius = size.width * 0.5f,
                center = Offset(x = size.width * 0.8f, y = size.height * 0.2f)
            )
        }

        // CONTENIDO
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding() // Evita que el título choque con la barra de arriba
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Estudiantes",
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "${students.size} alumnos registrados",
                color = Color.Black.copy(alpha = 0.6f),
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
    // Tarjeta con fondo translúcido (Glassmorphism sutil)
    Surface(
        color = Color.Black.copy(alpha = 0.1f),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Círculo para la inicial
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = Color(0xFF3F51B5)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = student.nombre.take(1),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = student.nombre,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = student.curso_id,
                    color = Color.Black.copy(alpha = 0.5f),
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