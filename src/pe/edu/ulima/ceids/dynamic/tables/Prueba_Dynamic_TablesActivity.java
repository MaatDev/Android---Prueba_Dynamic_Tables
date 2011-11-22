package pe.edu.ulima.ceids.dynamic.tables;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class Prueba_Dynamic_TablesActivity extends Activity {
    
	private TableLayout tl_data;
	
	private int counter = 0;
	
	private static final TableLayout.LayoutParams TABLE_PARAM = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
	
//	private static final TableRow.LayoutParams ROW_PARAM=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
	
	private static final android.view.ViewGroup.LayoutParams ROW_PARAM = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
	
	private static final TableRow.LayoutParams ROW_CHILD_PARAM=new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,1);
	
	private ArrayList<TableRow> rowList;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.tl_data = (TableLayout) super.findViewById(R.id.tl_data);
        
        this.rowList = new ArrayList<TableRow>();
                
    }
    
    public void addRow(View v){
    	
    	TableRow row = new TableRow(this);
    	row.setLayoutParams(ROW_PARAM);
    	row.setWeightSum(4);
    	
    	Persona persona = this.generateNewPersona();
    	
    	TextView tv_id = new TextView(this);
    	tv_id.setLayoutParams(ROW_CHILD_PARAM);
    	tv_id.setText(String.valueOf(persona.getId()));
    	
    	TextView tv_name = new TextView(this);
    	tv_name.setLayoutParams(ROW_CHILD_PARAM);  
    	tv_name.setText(persona.getName());
    	
    	TextView tv_lastname = new TextView(this);
    	tv_lastname.setLayoutParams(ROW_CHILD_PARAM);  
    	tv_lastname.setText(persona.getLastname());
    	
    	Button button = new Button(this);
    	button.setLayoutParams(ROW_CHILD_PARAM);
    	button.setText(super.getText(R.string.delete));
    	button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				System.out.println(view.getParent().getParent());
				TableLayout table = (TableLayout) view.getParent().getParent();
				TableRow row = (TableRow) view.getParent();
				int rowIndex = table.indexOfChild(row);
				if(rowIndex-1>=0){
					Prueba_Dynamic_TablesActivity.this.tl_data.removeViewAt(rowIndex);
					Prueba_Dynamic_TablesActivity.this.rowList.remove(rowIndex-1);
				}
				
			}
		});
    	
    	row.addView(tv_id);
    	row.addView(tv_name);
     	row.addView(tv_lastname);
    	row.addView(button);
    	
    	this.rowList.add(row);
    	
    	this.tl_data.addView(row);    	
    	
    }
      
    public void deleteRow(View v){
    	
    	if(this.tl_data.getChildCount()-1>0){
    		this.tl_data.removeViewAt(this.tl_data.getChildCount()-1);
    		this.rowList.remove(this.tl_data.getChildCount()-1);
    	}
    }
    
    
    
    private Persona generateNewPersona(){
    	Persona persona = new Persona();
    	persona.setId(counter++);
    	persona.setName(this.randomString());
    	persona.setLastname(this.randomString());
    	return persona;
    }
    
    private String randomString(){
    	
    	StringBuilder builder = new StringBuilder();
    	Random random = new Random();
    	for(int i = 0; i<5;i++){
    		builder.append((char)(random.nextInt(80)+35));
    	}
    	return builder.toString();		
    	    	  	
    }
    
}