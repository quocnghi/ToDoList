package com.example.project_team2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    ListView lvCongViec;
    ArrayList<CongViec> arrayCongViec;
    CongViecAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCongViec = (ListView) findViewById(R.id.listviewCongViec);
        arrayCongViec = new ArrayList<>();

        adapter = new CongViecAdapter(this, R.layout.dong_cong_viec, arrayCongViec);
        lvCongViec.setAdapter(adapter);


        //tạo database
        database = new Database(this, "MyWork.sqlite", null, 1);

        //tạo bảng
        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200))");

        // INSERT database code
//      database.QueryData("INSERT INTO CongViec VALUES(null,'Đi Học') ");

//        private void GetdataCongViec () {
            //select data
            Cursor dataCongViec = database.Getdata("SELECT * FROM CongViec");
            arrayCongViec.clear();     //ngắt lặp lại dữ liệu
            while (dataCongViec.moveToNext()) {
                String ten = dataCongViec.getString(1);
//            Toast.makeText(this, ten,Toast.LENGTH_SHORT).show();
                int id = dataCongViec.getInt(0);
                arrayCongViec.add(new CongViec(id, ten));
            }
            adapter.notifyDataSetChanged();
//        }


    }
}
