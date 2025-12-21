package com.ris.tcdc.viewmodel

import androidx.lifecycle.ViewModel
import com.ris.tcdc.R
import com.ris.tcdc.model.BloodCell
import com.ris.tcdc.model.CellType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted

class BloodCountViewModel : ViewModel() {

        private val _counts =
                MutableStateFlow<Map<CellType, Int>>(
                        CellType.values().associateWith { 0 }
                )

        private val _history = MutableStateFlow<List<CellType>>(emptyList())

        val cells: StateFlow<List<BloodCell>> =
                combine(
                        _counts,
                        _history
                ) { counts: Map<CellType, Int>, _: List<CellType> ->
                        listOf(
                                BloodCell(CellType.NEUTROPHIL, "Neutrophil", R.drawable.neutrophil, counts[CellType.NEUTROPHIL]!!),
                                BloodCell(CellType.LYMPHOCYTE, "Lymphocyte", R.drawable.lymphocyte, counts[CellType.LYMPHOCYTE]!!),
                                BloodCell(CellType.EOSINOPHIL, "Eosinophil", R.drawable.eosinophil, counts[CellType.EOSINOPHIL]!!),
                                BloodCell(CellType.BASOPHIL, "Basophil", R.drawable.basophil, counts[CellType.BASOPHIL]!!),
                                BloodCell(CellType.MONOCYTE, "Monocyte", R.drawable.monocyte, counts[CellType.MONOCYTE]!!)
                        )
                }
                        .stateIn(
                                scope = viewModelScope,
                                started = SharingStarted.WhileSubscribed(5_000),
                                initialValue = emptyList()
                        )


        val total: StateFlow<Int> =
                _counts.map { it.values.sum() }
                        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

        fun increment(type: CellType) {
                if (total.value >= 100) return

                _counts.update { counts: Map<CellType, Int> ->
                        counts + (type to (counts[type]!! + 1))
                }

                _history.update { it + type }
        }

        fun undo() {
                val last = _history.value.lastOrNull() ?: return

                _counts.update { counts: Map<CellType, Int> ->
                        counts + (last to (counts[last]!! - 1))
                }

                _history.update { it.dropLast(1) }
        }
}
