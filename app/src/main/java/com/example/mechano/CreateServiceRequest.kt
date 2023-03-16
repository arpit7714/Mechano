package com.example.mechano

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*


class CreateServiceRequest : AppCompatActivity() {

    var tv_date: TextView? = null
    private val formatter= SimpleDateFormat("dd-MM-yyyy")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_service_request)

        val issueArray: Array<String> = resources.getStringArray(R.array.Issues_array)
        val brandArray: Array<String> = resources.getStringArray(R.array.brands_array)

        val staticAdapter1 = ArrayAdapter
            .createFromResource(
                this, R.array.Issues_array,
                R.layout.dropdown_item
            )
        val staticAdapter2 = ArrayAdapter
            .createFromResource(
                this, R.array.brands_array,
                R.layout.dropdown_item
            )
        val staticAdapter3 = ArrayAdapter
            .createFromResource(
                this, R.array.Time_array,
                R.layout.dropdown_item
            )


        val date = Date()
        val current = formatter.format(date)
        tv_date= findViewById<AutoCompleteTextView>(R.id.tv_date);
//        tv_date = findViewById(R.id.tv_date)
        tv_date?.text = current.toString()
        tv_date?.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,0,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our text view.
                    tv_date?.text =
                        (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
        val next_btn = findViewById<ImageButton>(R.id.sr_next_button)
        next_btn.setOnClickListener {
            val intent = Intent(this, Bookingsuccess::class.java)
            startActivity(intent)
        }

        var textInput1 = findViewById<AutoCompleteTextView>(R.id.spinner1);
        var textInput2 = findViewById<AutoCompleteTextView>(R.id.spinner2);
        var textInput3 = findViewById<AutoCompleteTextView>(R.id.spinner4);

        textInput1.setAdapter(staticAdapter1)
        textInput2.setAdapter(staticAdapter2)
        textInput3.setAdapter(staticAdapter3)


        textInput1.setOnItemClickListener { adapterView, view, i, l ->
            val writeIssue = findViewById<AutoCompleteTextView>(R.id.writeIssue)
            if (textInput1.text.toString() == issueArray[4]){
                    writeIssue.visibility = View.VISIBLE
            }else{
                writeIssue.visibility = View.GONE
            }
        }

        textInput2.setOnItemClickListener { adapterView, view, i, l ->
            val writeBrand = findViewById<AutoCompleteTextView>(R.id.writeBrand)
            if (textInput2.text.toString() == brandArray[5]){
                writeBrand.visibility = View.VISIBLE
            }else{
                writeBrand.visibility = View.GONE
            }
        }


    }
}
