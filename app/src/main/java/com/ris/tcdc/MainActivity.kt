package com.ris.tcdc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ris.tcdc.ui.screens.BloodCountScreen
import com.ris.tcdc.ui.theme.BloodCountTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent { BloodCountTheme { BloodCountScreen() } }
  }
}