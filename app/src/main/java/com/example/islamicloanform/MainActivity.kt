package com.example.islamicloanform
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.example.islamicloanform.ViewModel.LoanCalculator
import com.example.islamicloanform.ViewModel.LoanViewModel
import com.example.islamicloanform.ui.theme.IslamicLoanFormTheme

class MainActivity : ComponentActivity() {
    private val viewModel: LoanViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicLoanFormTheme {

                    CompositionLocalProvider(LocalContentColor provides Color.Black) {
                        LoanCalculator(viewModel)
                    }
            }
        }
    }
}

@Composable
fun MyApp() {
    IslamicLoanFormTheme {
        Surface {

        }
    }
}
