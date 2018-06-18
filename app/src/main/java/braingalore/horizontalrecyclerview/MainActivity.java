package braingalore.horizontalrecyclerview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    HorizontalAdapter adapter = new HorizontalAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageUtils.initialize(this);
        RecyclerView list = findViewById(R.id.my_recycler_view);
        list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        list.setAdapter(adapter);
        ImageView addImage = findViewById(R.id.add_image_view);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chooseImageIntent = IntentUtils.getPickImageIntent(MainActivity.this);
                startActivityForResult(chooseImageIntent, 1234);
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1234:
                Bitmap bitmap = ImageUtils.getImageFromResult(this, resultCode, data);
                if (bitmap != null) {
                    Bitmap resizedBitmap = ImageUtils.resize(
                            bitmap,
                            120,
                            120);
                    //mBinding.imageButton.setImageBitmap(resizedBitmap);
                    Uri mProfilePicUri = ImageUtils.getImageUri(this, resizedBitmap, "profile.jpg");
                    adapter.addItem(mProfilePicUri);
                    adapter.notifyDataSetChanged();
                    bitmap.recycle();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
