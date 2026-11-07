package com.example.menuapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });

        // Popup Menu

        Button btnPopup = findViewById(R.id.btnPopup);

        btnPopup.setOnClickListener(v -> {

            PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnPopup);

            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {

                int id = item.getItemId();

                if (id == R.id.edit) {
                    Toast.makeText(this, "Edit Selected", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if (id == R.id.delete) {
                    Toast.makeText(this, "Delete Selected", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if (id == R.id.share) {
                    Toast.makeText(this, "Share Selected", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            });

            popupMenu.show();
        });

        // Floating Menu

        Button btnFloating = findViewById(R.id.btnFloating);
        Button btnStudent = findViewById(R.id.btnStudent);
        Button btnTeacher = findViewById(R.id.btnTeacher);
        Button btnCourse = findViewById(R.id.btnCourse);

        btnFloating.setOnClickListener(v -> {

            if (btnStudent.getVisibility() == View.GONE) {

                btnStudent.setVisibility(View.VISIBLE);
                btnTeacher.setVisibility(View.VISIBLE);
                btnCourse.setVisibility(View.VISIBLE);

            } else {

                btnStudent.setVisibility(View.GONE);
                btnTeacher.setVisibility(View.GONE);
                btnCourse.setVisibility(View.GONE);
            }
        });

        btnStudent.setOnClickListener(v ->
                Toast.makeText(this, "Add Student Selected", Toast.LENGTH_SHORT).show());

        btnTeacher.setOnClickListener(v ->
                Toast.makeText(this, "Add Teacher Selected", Toast.LENGTH_SHORT).show());

        btnCourse.setOnClickListener(v ->
                Toast.makeText(this, "Add Course Selected", Toast.LENGTH_SHORT).show());

        // Context Menu

        TextView txtContext = findViewById(R.id.txtContext);

        registerForContextMenu(txtContext);
    }

    // Options Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home) {
            Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.language) {
            Toast.makeText(this, "Language Selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.theme) {
            Toast.makeText(this, "Theme Selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.notifications) {
            Toast.makeText(this, "Notifications Selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.about) {
            Toast.makeText(this, "About Selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.logout) {
            Toast.makeText(this, "Logout Selected", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Context Menu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.copy) {
            Toast.makeText(this, "Copy Selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.rename) {
            Toast.makeText(this, "Rename Selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.remove) {
            Toast.makeText(this, "Remove Selected", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }
}