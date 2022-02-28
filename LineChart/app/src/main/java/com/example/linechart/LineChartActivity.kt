package com.example.linechart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*

class LineChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(lineChart) {
            // (1)
            axisLeft.isEnabled = true
            axisRight.isEnabled = false
            xAxis.isEnabled = false
            legend.isEnabled = true
            description.isEnabled = true

            // (2)
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(false)
            setPinchZoom(true)

            extraLeftOffset = 15f
            xAxis.setDrawGridLines(false)


        }

        //setLineChartData()

    }

    private fun setLineChartData() {
        val xValues = ArrayList<String>()
        xValues.add("Monday")
        xValues.add("Tuesday")
        xValues.add("Wednesday")
        xValues.add("Thursday")
        xValues.add("Friday")

        val dataset = ArrayList<ILineDataSet>()
        val week1 = ArrayList<Entry>()
        week1.add(Entry(1f, 125f))
        week1.add(Entry(2f, 50f))
        week1.add(Entry(3f, 185f))
        week1.add(Entry(4f, 275f))
        week1.add(Entry(5f, 85f))


        val lineDataSet = LineDataSet(week1, "week 1")
        lineChart.data = LineData(lineDataSet)

        lineDataSet.setDrawValues(true)
        lineDataSet.setDrawFilled(true)
        lineDataSet.lineWidth = 3f
        lineDataSet.valueTextSize = 20f
        lineDataSet.valueTextColor = R.color.purple_200
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet.fillColor = R.color.purple_500
        lineDataSet.fillAlpha = R.color.teal_700

        val xAxis = lineChart.xAxis
        with(xAxis){
            isEnabled = true
            setCenterAxisLabels(true)
            position = XAxis.XAxisPosition.BOTTOM
            textSize = 20f
            textColor = R.color.purple_700
            valueFormatter = IndexAxisValueFormatter(xValues)
        }
    }
}