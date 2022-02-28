package com.example.linechart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*

class LC : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpLineChart()
        setDataToLineChart()
    }

    private fun setUpLineChart() {
        with(lineChart) {

            axisRight.isEnabled = false
            animateX(1200, Easing.EaseInSine)

           description.isEnabled = true
//            description.text = "Sales Record"
//            description.setPosition((width/2).toFloat(),(height/2).toFloat())
            xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
            xAxis.valueFormatter = MyAxisFormatter()
            xAxis.isGranularityEnabled = true
            xAxis.granularity = 1f
            xAxis.setDrawLabels(true)
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(true)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            axisLeft.setDrawGridLines(false)
            extraRightOffset = 30f

            legend.isEnabled = true
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP;
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.form = Legend.LegendForm.LINE

        }
    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        private var items = arrayListOf("Milk", "Butter", "Cheese", "Ice cream", "Milkshake")

        override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
            val index = value.toInt()
            return if (index < items.size) {
                items[index]
            } else {
                null
            }
        }
    }

    private fun setDataToLineChart() {

        val weekOneSales = LineDataSet(week1(), "Week 1")
        weekOneSales.lineWidth = 3f
        weekOneSales.valueTextSize = 15f
        weekOneSales.mode = LineDataSet.Mode.CUBIC_BEZIER
        weekOneSales.color = ContextCompat.getColor(this, R.color.purple_700)
        weekOneSales.valueTextColor = ContextCompat.getColor(this, R.color.purple_700)
        weekOneSales.enableDashedLine(20F, 10F, 0F)

        val weekTwoSales = LineDataSet(week2(), "Week 3")
        weekTwoSales.lineWidth = 3f
        weekTwoSales.valueTextSize = 15f
        weekTwoSales.mode = LineDataSet.Mode.CUBIC_BEZIER
        weekTwoSales.color = ContextCompat.getColor(this, R.color.teal_200)
        weekTwoSales.valueTextColor = ContextCompat.getColor(this, R.color.teal_200)
        weekTwoSales.enableDashedLine(20F, 10F, 0F)


        var dataSet = ArrayList<ILineDataSet>()
        dataSet.add(weekOneSales)
        dataSet.add(weekTwoSales)

        var lineData = LineData(dataSet)
        lineChart.data = lineData

        lineChart.invalidate()
    }

    private fun week1(): ArrayList<Entry> {
        var sales = ArrayList<Entry>()
        sales.add(Entry(0f, 15f))
        sales.add(Entry(1f, 16f))
        sales.add(Entry(2f, 13f))
        sales.add(Entry(3f, 22f))
        sales.add(Entry(4f, 20f))
        return sales
    }

    private fun week2(): ArrayList<Entry> {
        var sales = ArrayList<Entry>()
        sales.add(Entry(0f, 11f))
        sales.add(Entry(1f, 13f))
        sales.add(Entry(2f, 18f))
        sales.add(Entry(3f, 16f))
        sales.add(Entry(4f, 22f))
        return sales
    }
}