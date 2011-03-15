package aini.nur;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.util.Log;

public class GrafikActivity extends Activity {
	int w=0;
	  private static final String TAG = "aini";

    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(new GrafikView(this));
        this.setContentView(R.layout.main);
        final GrafikView drawView = new GrafikView(this);
        drawView.requestFocus();
        LinearLayout upper = (LinearLayout) findViewById(R.id.LinearLayout02);
        upper.addView(drawView);
        final EditText nilaiY = (EditText) findViewById(R.id.EditText01);
        Button btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new Button.OnClickListener(){

      	  @Override
      	  public void onClick(View arg0) {
      		  int nilai=Integer.parseInt(nilaiY.getText().toString());
      		  
      		  if(w>5 || (nilai < 0 || nilai >50) )
      			  Toast.makeText(GrafikActivity.this, "data tdk benar",
                    Toast.LENGTH_SHORT).show();
      		  else
      		  {
      			  drawView.tambahtitik(200-nilai*4);
      			  w++;
      		  }
      		  nilaiY.setText("");
      		
      	  }
        });
        
    }
    private class GrafikView extends View {

        private Paint   mPaint = new Paint();
        private Canvas mCanvas;
        private int []titik = new int[5];;
        private int jumlahtitik =0;
		public GrafikView(Context context) {
			super(context);
			
          
		}
		
	  public void tambahtitik(int y)
	  {
		  if(jumlahtitik<5)
		  {
		  titik[jumlahtitik]=y;
		  jumlahtitik++;
		  }
	  }
		
				
		/* (non-Javadoc)
		 * @see android.view.View#onDraw(android.graphics.Canvas)
		 */
		@Override
		protected void onDraw(Canvas canvas) {
				
		    	          
                        
		    		// canvas.scale(4, 4);
	                canvas.drawColor(Color.WHITE);
	                canvas.translate(30, 30);
	                mPaint.setColor(Color.BLACK);
	                mPaint.setStrokeWidth(1);
	                canvas.drawLine(0, 0, 0, 200, mPaint);
	                canvas.drawLine(0, 200, 200,200, mPaint);
	             	for(int i=0;i<5;i++)
                	{
	             		mPaint.setColor(Color.BLUE);
	             		canvas.drawPoint(0, 40*(i+1), mPaint);
                	}
	                if(jumlahtitik >0)
	                	for(int i=0;i<jumlahtitik;i++)
	                	{
	                		mPaint.setColor(Color.RED);
	                		mPaint.setStrokeWidth(2);
	                		canvas.drawPoint(40*(i+2), titik[i], mPaint);
	                		Log.v(TAG, "i "+i);
	                		mPaint.setStrokeWidth(1);
	                	    mPaint.setColor(Color.YELLOW);
	                	    if(jumlahtitik>1 && i > 0)
	                	    canvas.drawLine(40*(i+1), titik[i-1], 40*(i+2),titik[i], mPaint);
	                	}
	               
	               
	                mPaint.setColor(Color.GREEN);
	                for (int i=0;i<=50;i=i+10)
	                	canvas.drawText(Integer.toString(i), -5, 200-(i*4), mPaint);	
	                
	                invalidate();
	            
		}
    	
		
    }

}