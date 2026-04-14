// BMI Controller of the MVC Architecture
package com.example.bmicalculator.controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import android.widget.*
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import com.example.bmicalculator.R
import com.example.bmicalculator.model.BMICalculatorResult
import com.example.bmicalculator.view.BMIUI

// MainActivity will be Controller for handling UI & API calls
class MainActivity : ComponentActivity() {
    // Declaring reference to BMIUI class/our view file
    private lateinit var ui: BMIUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to activity_main.xml layout
        setContentView(R.layout.activity_main)

        // Initialize the View (BMIUI)
        ui = BMIUI(this)

        // Set up click listener for Calculate Button
        ui.calculateButton.setOnClickListener {
            val heightText = ui.heightInput.text.toString() // Get height input
            val weightText = ui.weightInput.text.toString() // Get weight input

            // Validate inputs are not empty
            if (heightText.isNotEmpty() && weightText.isNotEmpty()) {
                val height = heightText.toDoubleOrNull() // Convert height input to Double
                val weight = weightText.toDoubleOrNull() // Convert weight input to Double

                // If inputs are valid, make an API call
                if (height != null && weight != null) {
                    callBMIAPI(height, weight)
                } else {
                    // Display toast for invalid input
                    Toast.makeText(this, getString(R.string.invalid_input), Toast.LENGTH_SHORT).show()
                }
            } else {
                // Display toast for missing input
                Toast.makeText(this, getString(R.string.missing_input), Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Call the BMI API
    private fun callBMIAPI(height: Double, weight: Double) {
        val client = OkHttpClient() // Create an OkHttpClient instance
        val url = "http://scai-bmi-calculator.us-west-2.elasticbeanstalk.com/api/BMI/calculateBMI?height=$height&weight=$weight" // Construct API URL

        val request = Request.Builder().url(url).build() // Build request

        // Asynchronous API call using OkHttp
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle API call failure on the main thread
                runOnUiThread {
                    Toast.makeText(this@MainActivity, getString(R.string.api_failure), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                // If API response
                if (response.isSuccessful) {
                    val responseBody = response.body // Get the response body
                    if (responseBody != null) {
                        val gson = Gson() // Create a Gson instance for JSON parsing
                        val responseString = responseBody.string() // Convert response body to string
                        try {
                            // Parse the response into the BMICalculatorResult model
                            val bmiData = gson.fromJson(responseString, BMICalculatorResult::class.java)
                            runOnUiThread {
                                // Determine color based on BMI value
                                val color = when {
                                    bmiData.bmi < 18 -> R.color.blue
                                    bmiData.bmi in 18.0..24.9 -> R.color.green
                                    bmiData.bmi in 25.0..29.9 -> R.color.purple
                                    else -> R.color.red
                                }
                                // Update UI with BMI result
                                ui.updateUI(bmiData.bmi, bmiData.risk, bmiData.more, color)
                                // Set up educate button with appropriate action
                                ui.setEducateButtonClickListener()
                            }
                        } catch (e: Exception) {
                            // Handle JSON parsing errors
                            runOnUiThread {
                                ui.showToastMessage(this@MainActivity, getString(R.string.response_parse_error))
                            }
                        }
                    } else {
                        // If response body is null, show error message
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, getString(R.string.api_error), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // If unsuccessful response, show error message
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, getString(R.string.api_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMICalculatorTheme {
        Greeting("Android")
    }
}
