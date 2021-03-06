package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_project.Database.BlankContract;
import com.example.final_project.Database.BorrowersDB.Home_DataContact;
import com.example.final_project.Database.DatabaseHelper;
import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.example.final_project.firebaseConnection.ConnectionFireBase;


public class add_borrower extends AppCompatActivity {
    EditText id,email,amount;
    Button done;
    private String TAG = "add_borrow";
    private String i,e;
    private long a;
    private final DatabaseHelper mdbHelper = new DatabaseHelper(this);
    //TODO Read below line
    //Required sigaction, We can create this requester(add_borrow) Activity as dialog box in the HomeFragment to make application more user friendly?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_borrower_activity);

        id=findViewById(R.id.enter_id);
        email=findViewById(R.id.enter_mail);
        amount=findViewById(R.id.enter_amount);
        done=findViewById(R.id.button_done);

        done.setOnClickListener(v -> {
            //email -> data in database
            if(!id.getText().toString().trim().isEmpty() && !amount.getText().toString().trim().isEmpty()) {
                //fetching values from edit texts
                i = id.getText().toString().trim();
                e = email.getText().toString().trim();
                a = Long.parseLong(amount.getText().toString().trim());
                boolean rowCount = false;
                ConnectionFireBase connection = new ConnectionFireBase();
                //save dataContact is a method in database class which inserts the values to the table
                rowCount  = connection.pushNotification(i,e,a,mdbHelper,getApplicationContext());

                if(rowCount) {
                Toast.makeText(add_borrower.this, "inserted", Toast.LENGTH_SHORT).show();
            }

            else{
                Toast.makeText(add_borrower.this, "Failed to put your data", Toast.LENGTH_SHORT).show();
            }
            this.finish();
            this.finishActivity(0);
            }else{
                if(amount.getText().toString().trim().isEmpty())
                    Toast.makeText(getApplicationContext(),"You can't left amount empty",Toast.LENGTH_SHORT).show();

                if(id.getText().toString().trim().isEmpty())
                    Toast.makeText(getApplicationContext(),"You can't left id empty",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
