package in.ris.tcdc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.Integer;
import java.util.Stack;
import android.util.Log;

 
public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        int totalCount = 0;
        int neutrophil = 0;
        int basophil = 0;
        int eosinophil = 0;
        int monocyte = 0;
        int lymphocyte = 0;
        
        final TextView totalView = (TextView)findViewById(R.id.total);
        
        final int[] myV = {totalCount,neutrophil,basophil,eosinophil,monocyte,lymphocyte};
        
        final Stack<String> stack = new Stack<String>();
        
        ImageView neutro = (ImageView)findViewById(R.id.neutrophil);
        neutro.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
            TextView neutroView = (TextView)findViewById(R.id.neutrophilTotal);
            myV[1]++;
            addToTotal(myV, totalView);
            neutroView.setText(Integer.toString(myV[1]));
            stack.push("Neutrophil");
            //Toast.makeText(getApplicationContext(),"Neutrophil " + myV[1],Toast.LENGTH_SHORT).show();
          }
        });
        
        ImageView lympho = (ImageView)findViewById(R.id.lympho);
        lympho.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
            TextView lymphoView = (TextView)findViewById(R.id.lymphoTotal);
            myV[4]++;
            addToTotal(myV, totalView);
            lymphoView.setText(Integer.toString(myV[4]));
            stack.push("lymphocyte");
            //Toast.makeText(getApplicationContext(),"lymphocyte",Toast.LENGTH_SHORT).show();
          }
        });
        
        ImageView eosino = (ImageView)findViewById(R.id.eosino);
        eosino.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
            TextView eosinoView = (TextView)findViewById(R.id.eosinoTotal);
            myV[3]++;
            addToTotal(myV, totalView);
            eosinoView.setText(Integer.toString(myV[3]));
            stack.push("eosinophil");
            //Toast.makeText(getApplicationContext(),"eosinophil",Toast.LENGTH_SHORT).show();
          }
        });
        
        ImageView baso = (ImageView)findViewById(R.id.baso);
        baso.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
            TextView basoView = (TextView)findViewById(R.id.basoTotal);
            myV[2]++;
            addToTotal(myV, totalView);
            basoView.setText(Integer.toString(myV[2]));
            stack.push("Basophil");
            //Toast.makeText(getApplicationContext(),"Basophil",Toast.LENGTH_SHORT).show();
          }
        });

        ImageView mono = (ImageView)findViewById(R.id.mono);
        mono.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
            TextView monoView = (TextView)findViewById(R.id.monoTotal);
            myV[5]++;
            addToTotal(myV, totalView);
            monoView.setText(Integer.toString(myV[5]));
            stack.push("monocyte");
            //Toast.makeText(getApplicationContext(),"monocyte",Toast.LENGTH_SHORT).show();
          }
        });
        
        
        
        
        
        
    }
    
    public void addToTotal(int[] myV, TextView v) {
          myV[0]++;
          v.setText("Total: " + Integer.toString(myV[0]));
        }
    
    public void undo(Stack<String> stack) {
      String name = stack.pop();
      Log.d("TCDC-log", name);
    }
}