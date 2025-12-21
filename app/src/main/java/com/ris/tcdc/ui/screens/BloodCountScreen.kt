package com.ris.tcdc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ris.tcdc.ui.components.BloodCellRow
import com.ris.tcdc.ui.components.TotalCard
import com.ris.tcdc.viewmodel.BloodCountViewModel
import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import android.view.WindowManager
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodCountScreen(
  viewModel: BloodCountViewModel = viewModel()
) {
  KeepScreenOn()
  val cells by viewModel.cells.collectAsState()
  val total by viewModel.total.collectAsState()

  Scaffold(
    topBar = {
      TopAppBar(title = { Text("Blood Cell Counter") })
    }
  ) { padding ->

    Column(
      modifier = Modifier
        .padding(padding)
        .padding(16.dp)
        .fillMaxSize()
    ) {

      LazyColumn(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        items(cells) { cell ->
          BloodCellRow(
            cell = cell,
            onClick = { viewModel.increment(cell.type) }
          )
        }
      }

      Spacer(Modifier.height(16.dp))

      TotalCard(
        total = total,
        canUndo = total > 0,
        onUndo = {
          if (total > 0) viewModel.undo()
        })
    }
  }
}

@Composable
fun KeepScreenOn() {
  val context = LocalContext.current
  val activity = context as? Activity

  DisposableEffect(Unit) {
    activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

    onDispose {
      activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
  }
}