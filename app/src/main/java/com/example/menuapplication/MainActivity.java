package com.example.menuapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu;
import android.view.LayoutInflater;

import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);

                    return insets;
                });

        // Popup Menu
        Button btnPopup = findViewById(R.id.btnPopup);

        btnPopup.setOnClickListener(v -> {

            PopupMenu popupMenu =
                    new PopupMenu(MainActivity.this, btnPopup);

            popupMenu.getMenuInflater().inflate(
                    R.menu.popup_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {

                int id = item.getItemId();

                if (id == R.id.edit) {
                    showToast("Edit Selected");
                    return true;
                } else if (id == R.id.delete) {
                    showToast("Delete Selected");
                    return true;
                } else if (id == R.id.share) {
                    showToast("Share Selected");
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
                showToast("Add Student Selected"));

        btnTeacher.setOnClickListener(v ->
                showToast("Add Teacher Selected"));

        btnCourse.setOnClickListener(v ->
                showToast("Add Course Selected"));

        // Context Menu
        TextView txtContext = findViewById(R.id.txtContext);

        registerForContextMenu(txtContext);

        // Dialog Buttons
        Button btnSimple = findViewById(R.id.btnSimple);
        Button btnSingle = findViewById(R.id.btnSingle);
        Button btnMultiple = findViewById(R.id.btnMultiple);

        btnSimple.setOnClickListener(v -> showSimpleDialog());

        btnSingle.setOnClickListener(v -> showSingleDialog());

        btnMultiple.setOnClickListener(v -> showMultipleDialog());
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
            showToast("Home Selected");
            return true;

        } else if (id == R.id.language) {
            showToast("Language Selected");
            return true;

        } else if (id == R.id.theme) {
            showToast("Theme Selected");
            return true;

        } else if (id == R.id.notifications) {
            showToast("Notifications Selected");
            return true;

        } else if (id == R.id.about) {
            showToast("About Selected");
            return true;

        } else if (id == R.id.logout) {
            showToast("Logout Selected");
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
            showToast("Copy Selected");
            return true;

        } else if (id == R.id.rename) {
            showToast("Rename Selected");
            return true;

        } else if (id == R.id.remove) {
            showToast("Remove Selected");
            return true;
        }

        return super.onContextItemSelected(item);
    }

    // 1. Simple Custom Dialog
    private void showSimpleDialog() {

        View view = LayoutInflater.from(this)
                .inflate(R.layout.dialog_custom, null);

        TextView title = view.findViewById(R.id.dialogTitle);
        TextView message = view.findViewById(R.id.dialogMessage);

        title.setText("Simple Dialog");
        message.setText("Welcome! This is a Custom Dialog Box.");

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("OK", (d, which) ->
                        showToast("OK Clicked"))
                .setNegativeButton("Cancel", (d, which) ->
                        showToast("Cancelled"))
                .create();

        dialog.show();
    }

    // 2. Single Choice Custom Dialog
    private void showSingleDialog() {

        View view = LayoutInflater.from(this)
                .inflate(R.layout.dialog_single, null);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", (d, which) ->
                        showToast("Cancelled"))
                .create();

        dialog.setOnShowListener(d -> {

            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setOnClickListener(v -> {

                        int selectedId =
                                radioGroup.getCheckedRadioButtonId();

                        if (selectedId == -1) {

                            showToast("Please select an option");

                        } else {

                            String selected = "";

                            if (selectedId == R.id.radioJava) {
                                selected = "Java";
                            } else if (selectedId == R.id.radioPython) {
                                selected = "Python";
                            } else if (selectedId == R.id.radioCpp) {
                                selected = "C++";
                            } else if (selectedId == R.id.radioKotlin) {
                                selected = "Kotlin";
                            }

                            showToast("Selected: " + selected);
                            dialog.dismiss();
                        }
                    });
        });

        dialog.show();
    }

    // 3. Multiple Choice Custom Dialog
    private void showMultipleDialog() {

        View view = LayoutInflater.from(this)
                .inflate(R.layout.dialog_multiple, null);

        CheckBox checkJava = view.findViewById(R.id.checkJava);
        CheckBox checkPython = view.findViewById(R.id.checkPython);
        CheckBox checkCpp = view.findViewById(R.id.checkCpp);
        CheckBox checkKotlin = view.findViewById(R.id.checkKotlin);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("OK", (d, which) -> {

                    StringBuilder result = new StringBuilder();

                    if (checkJava.isChecked()) {
                        result.append("Java ");
                    }

                    if (checkPython.isChecked()) {
                        result.append("Python ");
                    }

                    if (checkCpp.isChecked()) {
                        result.append("C++ ");
                    }

                    if (checkKotlin.isChecked()) {
                        result.append("Kotlin ");
                    }

                    if (result.length() > 0) {
                        showToast("Selected: " + result);
                    } else {
                        showToast("No option selected");
                    }
                })
                .setNegativeButton("Cancel", (d, which) ->
                        showToast("Cancelled"))
                .create();

        dialog.show();
    }

    // Common Toast Method
    private void showToast(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}