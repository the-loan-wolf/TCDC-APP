package com.ris.tcdc.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ris.tcdc.model.BloodCell
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodCellRow(
    cell: BloodCell,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current
    Card(
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            onClick()
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = cell.count.toString(),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.width(60.dp)
            )

            Spacer(Modifier.width(16.dp))

            Image(
                painter = painterResource(cell.icon),
                contentDescription = cell.name,
                modifier = Modifier.size(110.dp)
            )

            Spacer(Modifier.width(16.dp))

            Text(cell.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
