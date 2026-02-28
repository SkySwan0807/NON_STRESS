package com.example.profedata.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.profedata.database.local.entities.TareaEntity
import com.example.profedata.ui.viewmodels.ViewModelTasks

@Composable
fun TasksScreen(
    viewModel: ViewModelTasks = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState(initial = emptyList())

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFF8F9FA))
    )

    Box(modifier = Modifier.fillMaxSize()) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(brush = backgroundGradient)
            drawCircle(
                color = Color(0xFFFFF3E0),
                radius = size.width * 0.6f,
                center = Offset(x = size.width * 0.1f, y = size.height * 0.15f)
            )
        }

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
                text = "Tienes ${tasks.size} actividades registradas",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(tasks) { tarea ->
                    TaskItem(tarea, viewModel.calcularPrioridad(tarea.fechaEntrega), viewModel.formatDate(tarea.fechaEntrega))
                }
            }
        }
    }
}

@Composable
fun TaskItem(tarea: TareaEntity, prioridad: String, fecha: String) {

    val priorityColor = when (prioridad) {
        "Alta" -> Color(0xFFE74C3C)
        "Media" -> Color(0xFFF1C40F)
        else -> Color(0xFF2ECC71)
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
                    text = tarea.titulo,
                    color = Color(0xFF2D3436),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    text = tarea.descripcion,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = fecha,
                    color = Color(0xFF2D3436),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}