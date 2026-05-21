package com.example.project100.system

fun calculateTotalProgress(pushUps: Int, sitUps: Int, squats: Int, running: Double): Float {
    val p1 = (pushUps / 100f).coerceAtMost(1f)
    val p2 = (sitUps / 100f).coerceAtMost(1f)
    val p3 = (squats / 100f).coerceAtMost(1f)
    val p4 = (running.toFloat() / 10f).coerceAtMost(1f)
    return (p1 + p2 + p3 + p4) / 4f
}
