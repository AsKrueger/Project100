package com.example.project100.system

object SystemMessages {
    private val AGGRESSIVE = listOf(
        "SYSTEM_ALERT: SEDENTARY_BIOMASS_DETECTED. INITIATE_PROTOCOL.",
        "FAILURE TO EVOLVE IS A DEATH SENTENCE.",
        "THE SYSTEM DEMANDS DATA. START THE QUEST.",
        "WEAKNESS IS A CHOICE. THE SYSTEM IS WATCHING.",
        "IDLE STATUS DETECTED. RECTIFY IMMEDIATELY."
    )

    private val PROGRESS = listOf(
        "ANALYZING_MUSCLE_FIBERS... ADAPTATION_IN_PROGRESS.",
        "DISCIPLINE_CORE: STABILIZING.",
        "KINETIC_ENERGY_RESERVES: INCREASING.",
        "BIOMETRIC_EVOLUTION_DETECTED. KEEP GOING.",
        "SYSTEM_OPTIMIZATION: 45% COMPLETED. MAINTAIN INTENSITY."
    )

    private val COMPLETED = listOf(
        "IDENTITY_VERIFIED: APEX_PREDATOR_STATUS.",
        "DAILY_QUEST_CLEARED. ENJOY_THE_STRENGTH.",
        "YOU HAVE SURVIVED THE SYSTEM. FOR TODAY.",
        "LIMITS_REMOVED. YOU ARE EVOLVING.",
        "RANK_EXPANSION_POSSIBLE. PROTOCOL SUCCESSFUL."
    )

    private val PUNISHMENT = listOf(
        "SYSTEM_PENALTY_ACTIVE. THE DEBT MUST BE PAID.",
        "FAILURE HAS CONSEQUENCES. COMMENCE PURGE.",
        "YOUR WILL IS WEAK. RECONSTRUCT IT THROUGH PAIN.",
        "ACCESS RESTRICTED. COMPLY OR PERISH.",
        "BURPEES ARE THE ONLY LANGUAGE THE SYSTEM SPEAKS NOW."
    )

    fun getMessageForProgress(progress: Float, hasPunishment: Boolean = false): String {
        return when {
            hasPunishment -> PUNISHMENT.random()
            progress >= 1.0f -> COMPLETED.random()
            progress > 0.1f -> PROGRESS.random()
            else -> AGGRESSIVE.random()
        }
    }
}
