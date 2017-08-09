package application1.com.aula1gdkandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button botao;
    EditText editText;
    Bundle bundle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bundle = savedInstanceState;
        //bundle.putString("txt", editText.getText().toString());
        //Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        botao = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editText2);
        //Intent out extra passo itens para outra activity

        botao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Secundaria secundaria = new Secundaria();
                Intent intent = new Intent();
                intent.putExtra("txt",editText.getText().toString());
                secundaria.startActivity(intent, null);

            }
        });


    }

}

