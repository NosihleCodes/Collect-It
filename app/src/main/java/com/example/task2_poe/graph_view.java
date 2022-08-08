package com.example.task2_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class graph_view extends AppCompatActivity {

    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);

        graphView = findViewById(R.id.idGraphView);

        //data to the graph view
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{

                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 4),
                new DataPoint(3, 9),
                new DataPoint(4, 6),
                new DataPoint(5, 3),
                new DataPoint(6, 6),
                new DataPoint(7, 1),
                new DataPoint(8, 2)
        });

        graphView.setTitle("The goal in graph view");

        graphView.setTitleColor(R.color.purple_200);

        graphView.setTitleTextSize(18);

        graphView.addSeries(series);
    }
}