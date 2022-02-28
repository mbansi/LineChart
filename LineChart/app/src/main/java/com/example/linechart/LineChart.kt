package com.example.linechart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*

class LineChart : AppCompatActivity(){

    private var salesList = ArrayList<SalesRecord>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpLineChart()
        setUpLineChartData()
    }

    private fun setUpLineChart() {
        with(lineChart) {
            axisRight.isEnabled = false
            animateX(1000, Easing.EaseInSine)
            xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
            xAxis.valueFormatter = MyAxisFormatter()
            xAxis.setDrawLabels(true)
        }
    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            return if (index < salesList.size) {
                salesList[index].item
            } else {
                ""
            }
        }
    }

    private fun setUpLineChartData() {

//        val entry1: ArrayList<Entry> = ArrayList()
//        salesList = week1()
//
//        for (i in salesList.indices) {
//            val salesModel = salesList[i]
//            entry1.add(Entry(i.toFloat(), salesModel.sales.toFloat()))
//        }

//        val entry2: ArrayList<Entry> = ArrayList()
//        salesList = week2()
//
//        for (i in salesList.indices) {
//            val salesModel = salesList[i]
//            entry2.add(Entry(i.toFloat(), salesModel.sales.toFloat()))
//        }

        val weekOneSales = LineDataSet(week1(),"Week 1")
        weekOneSales.lineWidth = 3f
        weekOneSales.valueTextSize = 20f
        weekOneSales.mode = LineDataSet.Mode.CUBIC_BEZIER

//        val weekTwoSales = LineDataSet(entry2,"Week 2")
//        weekTwoSales.lineWidth = 3f
//        weekTwoSales.valueTextSize = 20f
//        weekTwoSales.color = R.color.black
//        weekTwoSales.mode = LineDataSet.Mode.CUBIC_BEZIER

        var dataSet = ArrayList<ILineDataSet>()
        dataSet.add(weekOneSales)
       // dataSet.add(weekTwoSales)

        var lineData = LineData(dataSet)
        lineChart.data = lineData

        var items = ArrayList<String>()
        items.add("Milk")
        items.add("Butter")
        items.add("Ice cream")
        items.add("Cheese")
        items.add("Milk bar")
      //  lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(items)
        lineChart.invalidate()
    }

    private class AxisFormatter : IndexAxisValueFormatter(){
        override fun getFormattedValue(value: Float): String {
            return super.getFormattedValue(value)
        }
    }

//    private fun week1() : ArrayList<SalesRecord>{
//        var sales = ArrayList<SalesRecord>()
//        sales.add(SalesRecord("Milk",20))
//        sales.add(SalesRecord("Butter",16))
//        sales.add(SalesRecord("Icecream",12))
//        sales.add(SalesRecord("Cheese",25))
//        sales.add(SalesRecord("Milkbar",22))
//
//        return sales
//    }

    private fun week1() : ArrayList<Entry>{
        var sales = ArrayList<Entry>()
        sales.add(Entry(0f,20f))
        sales.add(Entry(0f,16f))
        sales.add(Entry(0f,12f))
        sales.add(Entry(0f,22f))
        sales.add(Entry(0f,20f))
        return sales
    }

    private fun week2() : ArrayList<SalesRecord>{
        var sales = ArrayList<SalesRecord>()
        sales.add(SalesRecord("Milk",12))
        sales.add(SalesRecord("Butter",18))
        sales.add(SalesRecord("Icecream",22))
        sales.add(SalesRecord("Cheese",25))
        sales.add(SalesRecord("Milkbar",20))

        return sales
    }
}