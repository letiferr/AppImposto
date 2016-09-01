package com.example.leticia.imposto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button btCalcular;
    public EditText edtSalario;
    public TextView txtImposto, txtINSS, txtSalarioBruto, txtResultado;
    public double salarioBruto, salarioDescontado, resINSS, resImposto, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarElementos();

    }

    private void iniciarElementos() {
        btCalcular= (Button) findViewById(R.id.btnCalc);
        edtSalario= (EditText) findViewById(R.id.edtxSalario);
        txtImposto= (TextView) findViewById(R.id.txtImposto);
        txtINSS= (TextView) findViewById(R.id.txtINSS);
        txtSalarioBruto= (TextView) findViewById(R.id.txtSalario);
        txtResultado= (TextView) findViewById(R.id.txtResultado);


    }
    public void onClick(View v){
        salarioBruto= Double.parseDouble(edtSalario.getText().toString());

        txtINSS.setText("Descontos INSS: R$ " + calcularINSS(salarioBruto));
        txtImposto.setText("Descontos IRRF: R$ " +String.format("%.2f",calcularImposto(salarioDescontado)));
        txtResultado.setText("Salário Liquido: R$ " +String.format("%.2f",resultado));

    }


    private double calcularINSS(double slBruto) {
        if (slBruto<=1556.94){
            resINSS= (slBruto*0.08);
            salarioDescontado=slBruto-resINSS;
        }

        else if(slBruto> 1556.94 && slBruto<=2594.92){
            resINSS= (slBruto*0.09);
            salarioDescontado=slBruto-resINSS;
        }
        else if (slBruto>2594.92 && slBruto<=5189.82){
            resINSS= (slBruto*0.11);
            salarioDescontado=slBruto-resINSS;
        }
        else if (slBruto>5189.82){
            resINSS=570.88;
            salarioDescontado=slBruto-resINSS;
        }
        return resINSS;
    }

    private double calcularImposto(double slDescontado) {
        if (slDescontado <= 1903.98) {
            resImposto = 0;
            resultado = salarioBruto - resINSS - resImposto;

        } else if (slDescontado > 1903.98 && slDescontado <= 2826.65) {
            resImposto = (slDescontado * 0.075);
            resImposto = resImposto - 142.80;
            resultado = salarioBruto - resINSS - resImposto;
        } else if (slDescontado > 2826.65 && slDescontado <= 3751.01) {
            resImposto = (slDescontado * 0.15);
            resImposto = resImposto - 354.80;
            resultado = salarioBruto - resINSS - resImposto;
        } else if (slDescontado > 3751.01 && slDescontado <= 4664.68) {
            resImposto = (slDescontado * 0.225);
            resImposto = resImposto - 636.13;
            resultado = salarioBruto - resINSS - resImposto;

        } else if (slDescontado > 4664.68) {
            resImposto = (slDescontado * 0.275);
            resImposto = resImposto - 869.36;
            resultado = salarioBruto - resINSS - resImposto;
        }
        return resImposto;
    }
}
