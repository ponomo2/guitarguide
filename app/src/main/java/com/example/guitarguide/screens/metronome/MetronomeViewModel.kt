package com.example.guitarguide.screens.metronome

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guitarguide.metronome.Metronome
import kotlinx.coroutines.launch

class MetronomeViewModel(): ViewModel() {

    val lowestTempo: Long = Metronome.BPM_LOWER_THRESHOLD
    val highestTempo: Long = Metronome.BPM_UPPER_THRESHOLD

    fun startMetronome(bpm: String) {
        viewModelScope.launch {
            try {
                Metronome.start(bpm.toLong())
            }
            catch (e: NumberFormatException) {
                Log.d("ok", "notok")
            }
        }
    }

    fun restartMetronome(bpm: String) {
        try {
            Metronome.stop()
            Metronome.start(bpm.toLong())
        }
        catch (e: NumberFormatException) {
        }
    }

    fun stopMetronome() {
        Metronome.stop()
    }
}