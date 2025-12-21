package com.ris.tcdc.model
enum class CellType {
    NEUTROPHIL,
    LYMPHOCYTE,
    EOSINOPHIL,
    BASOPHIL,
    MONOCYTE
}

data class BloodCell(
    val type: CellType,
    val name: String,
    val icon: Int,
    val count: Int
)

