package com.example.profedata.ui.screens

import androidx.compose.ui.tooling.preview.Preview
import com.example.profedata.ui.theme.ProfeDataTheme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
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
import com.example.profedata.ui.viewmodels.TasksViewModel

// El modelo de datos debe ser accesible tanto aquí como en el ViewModel
data class Task(
    val id: Int,
    val title: String,
    val subject: String,
    val dueDate: String,
    val priority: String
)

@Composable
fun TasksScreen(
    viewModel: TasksViewModel = hiltViewModel()
) {
    // 1. Observamos la lista de tareas que viene del ViewModel
    val tasks by viewModel.tasks.collectAsState()

    // Fondo blanco con degradado sutil
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFF8F9FA))
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // 2. Fondo Decorativo (Canvas)
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(brush = backgroundGradient)
            drawCircle(
                color = Color(0xFFFFF3E0), // Naranja pastel sutil
                radius = size.width * 0.6f,
                center = Offset(x = size.width * 0.1f, y = size.height * 0.15f)
            )
        }

        // 3. Contenido Principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Tareas",
                color = Color(0xFF2D3436),
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "Tienes ${tasks.size} actividades pendientes",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Lista que se actualiza según el ViewModel
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(tasks) { task ->
                    TaskItem(task)
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    // Definimos el color de la barra lateral según la prioridad
    val priorityColor = when(task.priority) {
        "Alta" -> Color(0xFFE74C3C)  // Rojo
        "Media" -> Color(0xFFF1C40F) // Amarillo
        else -> Color(0xFF2ECC71)    // Verde
    }

    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicador vertical de prioridad
            Surface(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight(),
                color = priorityColor,
                shape = RoundedCornerShape(2.dp)
            ) {}

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    color = Color(0xFF2D3436),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    text = task.subject,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            // Fecha de entrega
            Column(horizontalAlignment = Alignment.End) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = task.dueDate,
                    color = Color(0xFF2D3436),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TasksScreenPreview() {
    ProfeDataTheme {
        TasksScreen()
    }
}