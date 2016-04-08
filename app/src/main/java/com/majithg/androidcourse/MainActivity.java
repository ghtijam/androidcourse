package com.majithg.androidcourse;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText myInput;
    TextView myText;

    Button addBtt, delBtt;

    DBhelper dBhelper;


    private static final String TABLE_NAME = "Products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCTNAME = "productname";

    Products mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myInput = (EditText) findViewById(R.id.editText);
        myText = (TextView) findViewById(R.id.textView);
        addBtt = (Button) findViewById(R.id.button);
        delBtt = (Button) findViewById(R.id.button2);

        dBhelper = new DBhelper(this, null, null, 1);

        printDB(); // it will display existing...



        addBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addButton();
            }
        });

        delBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteButton();
            }
        });

    }


    public void printDB(){

        String output = dBhelper.databaseToSting();
        myText.setText(output);
        myInput.setText("");
    }


    public void addButton(){

       String input = myInput.getText().toString();
//        Products mProduct = new Products(input);
        mProduct = new Products(input);
        dBhelper.addProduct(mProduct);

        printDB(); // i think... this is like refresh...

    }


    public void deleteButton(){
        String todelete = myInput.getText().toString();
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();

        // plz try to change... id also... try...
//        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " WHERE " + COLUMN_PRODUCTNAME + " = '" + todelete + "' ", null);
//        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM "+TABLE_NAME, null);
//        int changeID = cursor.getPosition();
//        cursor.moveToNext();

        dBhelper.deleteProduct(todelete);

//        ContentValues contentValues =  new ContentValues();
//        contentValues.put(COLUMN_ID, changeID);

//        cursor.close();
        sqLiteDatabase.close();
//        contentValues.clear();

        printDB(); // i think... this is like refresh...
    }
}
