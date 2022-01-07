package loi.project.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        var tvAgeInMinutes = findViewById<TextView>(R.id.tvAgeInMinutes)
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }

    }

    private fun clickDatePicker(view: View){
        var tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        var tvAgeInMinutes = findViewById<TextView>(R.id.tvAgeInMinutes)
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        var dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,
                    "The chosen date is $selectedDayOfMonth/${selectedMonth + 1}/$selectedYear",
                    Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time/60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvAgeInMinutes?.text = differenceInMinutes.toString()
            }, year, month, day)


        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }



}