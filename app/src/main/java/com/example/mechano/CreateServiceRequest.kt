package com.example.mechano

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class CreateServiceRequest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_service_request)
        var dropdown1 = findViewById<Spinner>(R.id.spinner1)
        var dropdown2 = findViewById<Spinner>(R.id.spinner2)
        var dropdown3 = findViewById<Spinner>(R.id.spinner3)

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
                android.R.layout.simple_spinner_item
            )

        staticAdapter1
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticAdapter2
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticAdapter3
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown1.adapter = staticAdapter1
        dropdown2.adapter = staticAdapter2
        dropdown3.adapter = staticAdapter3

    }
}