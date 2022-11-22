package com.sabeeh.graphkotlin.view.custom

import android.R
import android.content.Context
import android.graphics.Typeface
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.sabeeh.graphkotlin.databinding.RadarMarkerviewBinding
import java.lang.String
import java.text.DecimalFormat


class RadarMarkerView(context: Context, layoutResource: Int) :
    MarkerView(context, layoutResource) {
    private val tvContent: TextView
    //private lateinit var binding:RadarMarkerviewBinding
    //private val format: DecimalFormat = DecimalFormat("##0")

    init {
        //binding = RadarMarkerviewBinding.inflate(com.sabeeh.graphkotlin.R.layout.radar_markerview,false)
        tvContent = findViewById(com.sabeeh.graphkotlin.R.id.tvContent)
        tvContent.setTypeface(Typeface.MONOSPACE)
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    /*fun refreshContent(e: Map.Entry<*, *>, highlight: Highlight?) {
        tvContent.text = String.format("%s %%", format.format(e.ge))
        super.refreshContent(e, highlight)
    }*/

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height - 10).toFloat())
    }
}