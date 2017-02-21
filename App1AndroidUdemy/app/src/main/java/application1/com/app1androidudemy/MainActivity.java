package application1.com.app1androidudemy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Declarado aqui fora para ser visto pela função buttonClickTwo
    TextView textTwo;
    //O método on create está relacionado a quando a tela é criada para o usuário
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Text view referencia uma area de texto, no caso achei firstText pelo findViewById usando o id que setei no activity_main.xml
        //o final nesse caso serve para ser enxergado por funções posteriormente criadas
        final TextView textOne = (TextView)findViewById(R.id.textOne);

        textTwo = (TextView)findViewById(R.id.textTwo);

        //Altera o texto, predomina esse
        textOne.setText("Primeira aplicação");

        //O mesmo, porém com botão
        Button buttonOne = (Button)findViewById(R.id.buttonOne);
        buttonOne.setText("Começar!");

        //registro evento de click
        buttonOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textOne.setText("O botão foi clicado");
            }
        });

    }

    //botao 2 é ativado ao clicar sobre o texto 2
    public void buttonTwoClick(View buttonClicked){
        textTwo.setText("Botao dois clicado");
    }


}
