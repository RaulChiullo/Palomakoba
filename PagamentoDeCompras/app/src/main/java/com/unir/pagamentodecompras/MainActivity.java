package com.unir.pagamentodecompras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double total, valordescontado1, valordescontado2, desconto,vpago,edtpago, troco ;
    String desc;
    EditText valorpago;
    Button btntotal, btnpagar;
    RadioGroup radiodesconto, radioproduto;
    CheckBox arroz, carne, pao, leite, ovos;
    TextView txttotal, aaa;
    RadioButton semdeconto, cincoporcento, dezporcento, quinzeporcento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnpagar=findViewById(R.id.btnpagar);
        valorpago=findViewById(R.id.edtvalor);
        btntotal=findViewById(R.id.btntotal);
        radiodesconto=findViewById(R.id.radioGroup2);
        radioproduto=findViewById(R.id.radioGroup);
        arroz=findViewById(R.id.ckarroz);
        carne=findViewById(R.id.ckcarne);
        pao=findViewById(R.id.ckpao);
        leite=findViewById(R.id.ckleite);
        ovos=findViewById(R.id.ckovos);
        txttotal=findViewById(R.id.txttotal);
        semdeconto=findViewById(R.id.rbsemdeconto);
        cincoporcento=findViewById(R.id.rb5);
        dezporcento=findViewById(R.id.rb10);
        quinzeporcento=findViewById(R.id.rb15);
        aaa=findViewById(R.id.aaa);

        btntotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = 0;

                if(arroz.isChecked()) {total += 3.50; }
                if(carne.isChecked()) {total += 12.30; }
                if(pao.isChecked()) {total += 2.20;}
                if(leite.isChecked()) {total += 5.50;}
                if(ovos.isChecked()) {total += 7.50;}


                String msg=String.valueOf(total);
                txttotal.setText("R$"+msg);
            }

        });
        btnpagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (radiodesconto.getCheckedRadioButtonId()) {
                    case R.id.rbsemdeconto:
                        desconto = 0;
                        desc = "0%";
                        break;
                    case R.id.rb5:
                        desconto = 0.05;
                        desc = "0%";
                        break;
                    case R.id.rb10:
                        desconto = 0.10;
                        desc = "10%";
                        break;
                    case R.id.rb15:
                        desconto = 0.15;
                        desc = "15%";
                        break;

                }
                valordescontado1 = total * desconto;
                valordescontado2 = total - valordescontado1;
                //String test=String.valueOf(valordescontado2);
                //aaa.setText(test);
                if (!valorpago.getText().toString().isEmpty()) {
                    vpago = Double.parseDouble(valorpago.getText().toString());
                } else {
                    edtpago = 0;
                }
                troco=vpago-valordescontado2;
                if (vpago == 0 || vpago < valordescontado2) {
                    AlertDialog.Builder janela = new AlertDialog.Builder(MainActivity.this);
                    janela.setTitle("AVISO");
                    janela.setMessage("Saldo insuficiente.");
                    janela.setNeutralButton("OK", null);
                    janela.show();
                } else {
                    AlertDialog.Builder janela = new AlertDialog.Builder(MainActivity.this);
                    janela.setTitle("AVISO");
                    janela.setMessage("Valor total da compra: " + valordescontado2 +
                            "\nDesconto: " + desc +
                            "\nValor pago: " + vpago +
                            "\nTroco: " + troco);
                    janela.setNeutralButton("OK", null);
                    janela.show();
                }
            }
        });
    }
}