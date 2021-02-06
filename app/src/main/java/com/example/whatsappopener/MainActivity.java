package com.example.whatsappopener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Intent.*;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText=findViewById(R.id.text1);
        Button btn=findViewById(R.id.btn);
        final String[] number = new String[1];
        if(getIntent().getAction()== ACTION_PROCESS_TEXT)
        {
            number[0] =getIntent().getCharSequenceArrayExtra(EXTRA_PROCESS_TEXT).toString();
        }
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                number[0] =editText.getText().toString();
                Log.i(String.valueOf(35), "onClick: "+ number[0]);
                startWhatsApp(number[0]);
            }
        });
//        startWhatsApp(number);
    }

    private void startWhatsApp(String number)
    {
        Intent i= new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp");
        if(number.charAt(0)=='+')
            number=number.substring(1);
        else if(number.length()==10)
            number="91"+number;
        i.setData(Uri.parse("https://wa.me/"+number));
        if(getPackageManager().resolveActivity(i,0)!=null)
        {
            startActivity(i);
        }
        else
            Toast.makeText(this,"App not installed",Toast.LENGTH_SHORT);
        finish();
    }
}