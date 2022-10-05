package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private Button buttonOk;
    private TextInputEditText etconfirmpass;
    private TextInputLayout itFullname, itEmail, itPass, itConfirmpass, itHomepage, itAbout;

    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private ImageView avatarImage;

    public void handleChangeAvatar(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonOk = findViewById(R.id.button_ok);
        avatarImage = findViewById(R.id.image_profile);
        itFullname = findViewById(R.id.layout_fullname);
        itEmail = findViewById(R.id.layout_email);
        itPass = findViewById(R.id.layout_password);
        itConfirmpass = findViewById(R.id.layout_confirm_password);
        etconfirmpass = findViewById(R.id.text_confirm_password);
        itHomepage = findViewById(R.id.layout_homepage);
        itAbout = findViewById(R.id.layout_about);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = itPass.getEditText().getText().toString();
                String confirmpass = itConfirmpass.getEditText().getText().toString();

                boolean cek = validateinfo(password, confirmpass);

                if (cek==true){
                    Toast.makeText(getApplicationContext(), "Data is valid", Toast.LENGTH_SHORT).show();
                    avatarImage.setDrawingCacheEnabled(true);
                    Bitmap b = avatarImage.getDrawingCache();
                    Intent move = new Intent(RegisterActivity.this, ProfileActivity.class);
                    move.putExtra("fullname", itFullname.getEditText().getText().toString());
                    move.putExtra("email", itEmail.getEditText().getText().toString());
                    move.putExtra("homepage", itHomepage.getEditText().getText().toString());
                    move.putExtra("about", itAbout.getEditText().getText().toString());
                    move.putExtra("Bitmap", b);
                    startActivity(move);
                }else {
                    Toast.makeText(getApplicationContext(), "Maaf, cek password kembali", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateinfo(String password, String confirmpass){
        if (!password.equals(confirmpass)){
            etconfirmpass.requestFocus();
            etconfirmpass.setError("Password tidak sama");
            return false;
        }else {
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            return;
        }if (requestCode == GALLERY_REQUEST_CODE){
            if (data!=null){
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatarImage.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(this, "Tidak bisa memuat gambar", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}
