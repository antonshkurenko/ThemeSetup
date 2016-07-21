package io.github.tonyshkurenko.themesetup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

  public static final int THEME_MATERIAL_LIGHT = 0;
  public static final int THEME_YOUR_CUSTOM_THEME = 1;

  static int sThemePosition;

  private Spinner spThemes;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onActivityCreateSetTheme(this);
    setContentView(R.layout.activity_main);
    setupSpinnerItemSelection();
  }

  private void setupSpinnerItemSelection() {
    spThemes = (Spinner) findViewById(R.id.spThemes);
    spThemes.setSelection(sThemePosition);
    sThemePosition = spThemes.getSelectedItemPosition();

    spThemes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

      @Override
      public void onItemSelected(AdapterView<?> parent, View view,
          int position, long id) {
        if (sThemePosition != position) {
          changeToTheme(MainActivity.this, position);
        }
        sThemePosition = position;
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

  }


  private static void changeToTheme(Activity activity, int theme) {
    sThemePosition = theme;
    activity.finish();
    activity.startActivity(new Intent(activity, activity.getClass()));
    activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
  }

  private static void onActivityCreateSetTheme(Activity activity) {
    switch (sThemePosition) {
      default:
      case THEME_MATERIAL_LIGHT:
        activity.setTheme(R.style.Theme_Material_Light);
        break;
      case THEME_YOUR_CUSTOM_THEME:
        activity.setTheme(R.style.Theme_FirstCoolTheme);
        break;
    }
  }
}
