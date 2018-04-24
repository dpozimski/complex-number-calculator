package com.dpozimski.complex_number_calculator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends Activity {
    private ComplexNumberCalculator calculator;
    private EditText etTextA;
    private EditText etTextAc;
    private EditText etTextB;
    private EditText etTextBc;
    private GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new ComplexNumberCalculator();
        etTextA = findViewById(R.id.etTextA);
        etTextAc = findViewById(R.id.etTextAc);
        etTextB = findViewById(R.id.etTextB);
        etTextBc = findViewById(R.id.etTextBc);
        graphView = findViewById(R.id.grapView);
        configureGraphStyle();

        Button buttonPlus = findViewById(R.id.btAdd);
        Button buttonMinus = findViewById(R.id.btMinus);
        View.OnClickListener btListener =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onClick(v);
            }
        };
        buttonPlus.setOnClickListener(btListener);
        buttonMinus.setOnClickListener(btListener);
    }

    private void configureGraphStyle() {
        graphView.getGridLabelRenderer().setGridColor(Color.parseColor("#304FFE"));
        graphView.getGridLabelRenderer().setHighlightZeroLines(false);
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(Color.parseColor("#304FFE"));
        graphView.getGridLabelRenderer().setVerticalLabelsColor(Color.parseColor("#304FFE"));
        graphView.getGridLabelRenderer().reloadStyles();
    }

    private void onClick(View view){
        float digitA = Float.parseFloat(etTextA.getText().toString());
        float digitAc = Float.parseFloat(etTextAc.getText().toString());
        float digitB = Float.parseFloat(etTextB.getText().toString());
        float digitBc = Float.parseFloat(etTextBc.getText().toString());

        ComplexNumber complexNumberA = new ComplexNumber(digitA, digitAc);
        ComplexNumber complexNumberB = new ComplexNumber(digitB, digitBc);

        ComplexNumber result;
        if(view.getId() == R.id.btAdd)
            result = calculator.add(complexNumberA, complexNumberB);
        else
            result = calculator.odd(complexNumberA, complexNumberB);

        showResult(result);
    }

    private void showResult(ComplexNumber result){
        showResultOnGraph(result);
        showResultOnToaster(result);
    }

    private void showResultOnToaster(ComplexNumber result) {
        Context context = getApplicationContext();
        Toast.makeText(context, "The result is: " + result.toString(), Toast.LENGTH_LONG).show();
    }

    private void showResultOnGraph(ComplexNumber result) {
        DataPoint initialPoint = new DataPoint(0,0);
        DataPoint complexNumberPoint = result.toDataPoint();
        DataPoint[] graphDataPoints = new DataPoint[2];

        boolean isResultGreaterOrEqualZero = result.isGreaterThanOrEqualZero();
        graphDataPoints[0] = isResultGreaterOrEqualZero ? initialPoint : complexNumberPoint;
        graphDataPoints[1] = isResultGreaterOrEqualZero ? complexNumberPoint : initialPoint;

        GraphView graph = findViewById(R.id.grapView);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(graphDataPoints);
        graph.removeAllSeries();
        graph.addSeries(series);
    }
}

