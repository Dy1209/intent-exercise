package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvAbout, tvFullname, tvEmail, tvHomepage;
    private Button buttonHomepage;
    private ImageView viewBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bitmap bitmap = (Bitmap)this.getIntent().getParcelableExtra("bitmap");
        String fullname = getIntent().getExtras().getString("fullname");
        String email = getIntent().getExtras().getString("email");
        String homepage = getIntent().getExtras().getString("homepage");
        String about = getIntent().getExtras().getString("about");

        viewBitmap = findViewById(R.id.image_profile);
        tvAbout = findViewById(R.id.label_about);
        tvFullname = findViewById(R.id.label_fullname);
        tvEmail = findViewById(R.id.label_email);
        tvHomepage = findViewById(R.id.label_homepage);
        buttonHomepage = findViewById(R.id.button_homepage);

        viewBitmap.setImageBitmap(bitmap);
        tvAbout.setText(about);
        tvFullname.setText(fullname);
        tvEmail.setText(email);
        tvHomepage.setText(homepage);

        buttonHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://www.youtube.com/channel/UCdZHSOnUgVsU7zUzOdkuoVw");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });


    }
}
