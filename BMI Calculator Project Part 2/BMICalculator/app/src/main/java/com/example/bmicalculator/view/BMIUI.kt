// BMI View of the MVC Architecture
// Handles UI logic and interactions for BMI Calc
package com.example.bmicalculator.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import android.widget.*
import android.content.Intent
import android.net.Uri
import android.view.View
import com.example.bmicalculator.R
import com.example.bmicalculator.controller.MainActivity
import android.content.Context

class BMIUI(private val activity: MainActivity) {

    // UI elements connected to the corresponding views in layout file
    val heightInput: EditText = activity.findViewById(R.id.inputHeight) // Input field for height
    val weightInput: EditText = activity.findViewById(R.id.inputWeight) // Input field for weight
    val calculateButton: Button = activity.findViewById(R.id.callBmiApiButton) // Button to calculate BMI

    private val bmiResult: TextView = activity.findViewById(R.id.bmiLabel) // Displays BMI result
    private val riskMessage: TextView = activity.findViewById(R.id.bmiMessage) // Displays BMI risk message
    private val moreInfo: TextView = activity.findViewById(R.id.moreInfo) // Displays links

    private val educateButton: Button = activity.findViewById(R.id.educateMeButton) // Button for link

    // Display toast function
    fun showToastMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    // Function to update the UI with BMI results, risk message, and additional info
    fun updateUI(bmi: Double, risk: String, more: List<String>, color: Int) {
        bmiResult.text = activity.getString(R.string.bmi_result, bmi) // Updates BMI result text
        riskMessage.text = activity.getString(R.string.risk_message, risk) // Updates risk message text
        moreInfo.text = activity.getString(R.string.more_info, more.joinToString(", ")) // Updates additional info text (links)
        moreInfo.visibility = View.VISIBLE // Makes "More Info" section visible upon calling api
        riskMessage.setTextColor(activity.resources.getColor(color, null)) // Changes color of risk message based on BMI
    }

    // Function to handle clicks on "Educate Me" button.
    fun setEducateButtonClickListener() {
        val cdcLink = "https://www.nhlbi.nih.gov/health/educational/lose_wt/index.htm" // Link to an site
        educateButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(cdcLink))
            activity.startActivity(intent) // Open link in browser
        }
    }
}
