package com.example.motiontubes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.motiontubes.data.local.AppDatabase
import com.example.motiontubes.data.repository.ProductRepository
import com.example.motiontubes.navigation.NavGraph
import com.example.motiontubes.ui.theme.MotionTheme
import com.example.motiontubes.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "motion_db"
        ).fallbackToDestructiveMigration().build()

        val repo = ProductRepository(db.productDao())
        val vm = ProductViewModel(repo)

        setContent {
            MotionTheme {
                NavGraph(vm)
            }
        }
    }
}