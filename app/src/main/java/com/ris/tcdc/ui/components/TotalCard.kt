package com.ris.tcdc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp

@Composable
fun TotalCard(
    total: Int,
    canUndo: Boolean,
    onUndo: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text("Total: ", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = total.toString(),
                    style = MaterialTheme.typography.headlineLarge
                )
            }

//            FloatingActionButton(
//                onClick = onUndo,
//                modifier = Modifier.size(80.dp),
//                containerColor = MaterialTheme.colorScheme.primary,
//                contentColor = MaterialTheme.colorScheme.onPrimary
//            ) {
//                Text("Undo ↺") // or Icon(Icons.Default.Undo, null)
//            }
//            IconButton(
//                onClick = onUndo,
//                enabled = canUndo,
//                modifier = Modifier.size(80.dp)
//            ) {
////                need to add this dep implementation("androidx.compose.material:material-icons-extended")
//                Icon(
//                    imageVector = Icons.Default.Undo,
//                    contentDescription = "Undo"
//                )
////                Text("Undo")
//            }
            val haptic = LocalHapticFeedback.current
            Button(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    onUndo()
                },
                shape = RoundedCornerShape(50)
            ) {
//                Icon(Icons.Filled.Undo, contentDescription = null)
//                Spacer(Modifier.width(8.dp))
                Text("Undo")
            }

        }
    }
}
