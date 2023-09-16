package com.cmesquita.realstation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cmesquita.realstation.ui.list.RealStateListScreen
import com.cmesquita.realstation.ui.theme.RealStationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RealStationTheme {
                RealStateListScreen()
            }
        }
    }
}
