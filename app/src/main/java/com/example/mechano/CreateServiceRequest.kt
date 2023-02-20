package com.example.mechano

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class CreateServiceRequest : AppCompatActivity() {

    var tv_date: TextView? = null
    private val formatter= SimpleDateFormat("dd-MM-yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_service_request)
        var dropdown1 = findViewById<Spinner>(R.id.spinner1)
        var dropdown2 = findViewById<Spinner>(R.id.spinner2)
        var dropdown4 = findViewById<Spinner>(R.id.spinner4)


        val staticAdapter1 = ArrayAdapter
            .createFromResource(
                this, R.array.Issues_array,
                android.R.layout.simple_spinner_item
            )
        val staticAdapter2 = ArrayAdapter
            .createFromResource(
                this, R.array.brands_array,
                android.R.layout.simple_spinner_item
            )
        val staticAdapter3 = ArrayAdapter
            .createFromResource(
                this, R.array.Time_array,
                android.R.layout.simple_expandable_list_item_1
            )

        staticAdapter1
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticAdapter2
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticAdapter3
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown1.adapter = staticAdapter1
        dropdown2.adapter = staticAdapter2
        dropdown4.adapter = staticAdapter3

        dropdown1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

        }


        val date = Date()
        val current = formatter.format(date)
        tv_date = findViewById(R.id.tv_date)
        tv_date?.text = current.toString()
        tv_date?.setOnClickListener {
            Toast.makeText(this, "button is clicked",Toast.LENGTH_LONG).show()

            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
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
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

    }
}