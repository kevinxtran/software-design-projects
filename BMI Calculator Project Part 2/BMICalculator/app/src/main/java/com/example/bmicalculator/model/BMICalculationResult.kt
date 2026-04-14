// BMI Model of the MVC Architecture
package com.example.bmicalculator.model

// Responsible for data class that holds responses from BMI API
data class BMICalculatorResult(
    val bmi: Double,      // Calculated BMI value
    val risk: String,     // Risk level label/category based on the BMI
    val more: List<String> // More information
)
