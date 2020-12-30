package hans.bser.garage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CharacterActivity extends AppCompatActivity {

    NavbarFragment navbarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        navbarFragment = new NavbarFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fgNavbar, navbarFragment)
                .commit();
    }
}