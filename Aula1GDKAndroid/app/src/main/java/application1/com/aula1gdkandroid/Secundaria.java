package application1.com.aula1gdkandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Secundaria extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda_tela);

        textView = (TextView)findViewById(R.id.textView);
        Intent intent = new Intent();
        textView.setText(intent.getStringExtra("txt"));
    }


}
