package com.example.islamicloanform.ViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel


class LoanViewModel : ViewModel() {
    private var _loanAmount by mutableStateOf(0f)
    val loanAmount: Float
        get() = _loanAmount

    private var _periodInMonths by mutableStateOf(0)
    val periodInMonths: Int
        get() = _periodInMonths

    private var _monthlyInstallment by mutableStateOf(0f)
    val monthlyInstallment: Float
        get() = _monthlyInstallment


    private var _maxInstallmentPercentage by mutableStateOf(0.3f)
    val maxInstallmentPercentage: Float
        get() = _maxInstallmentPercentage



    fun setMaxInstallmentPercentage(percentage: Float) {
        _maxInstallmentPercentage = percentage
    }

    fun setLoanAmount(amount: Float) {
        _loanAmount = amount
    }

    fun setPeriodInMonths(period: Int) {
        _periodInMonths = period
    }

    fun calculateMonthlyInstallment() {
        _monthlyInstallment = if (_periodInMonths != 0)
        {
            _loanAmount / _periodInMonths
        } else {
            0f
        }
    }
}

@Composable
fun LoanCalculator(viewModel: LoanViewModel ) {
    val loanAmount by rememberUpdatedState(newValue = viewModel.loanAmount)
    val periodInMonths by rememberUpdatedState(newValue = viewModel.periodInMonths)
    val monthlyInstallment by rememberUpdatedState(newValue = viewModel.monthlyInstallment)
    val maxInstallmentPercentage by rememberUpdatedState(newValue = viewModel.maxInstallmentPercentage)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = loanAmount.toString(),
            onValueChange = {
                viewModel.setLoanAmount(it.toFloatOrNull() ?: 0f)
            },
            label = { Text("Loan Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = periodInMonths.toString(),
            onValueChange = {
                viewModel.setPeriodInMonths(it.toIntOrNull() ?: 0)
            },
            label = { Text("Loan Period (Months)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = maxInstallmentPercentage.toString(),
            onValueChange = {
                viewModel.setMaxInstallmentPercentage(it.toFloatOrNull() ?: 0f)
            },
            label = { Text("Max Installment (% of Salary)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Button(
            onClick = {
                viewModel.calculateMonthlyInstallment()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            Text("Calcaulate Monthly Installment")
        }
        Text("Monthly Installment: $monthlyInstallment")
    }
}



