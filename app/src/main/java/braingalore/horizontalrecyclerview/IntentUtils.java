package braingalore.horizontalrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajitha3008 on 14/04/18.
 */

public class IntentUtils {

    public static Intent getGalleryIntent(Context context) {
        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();

        Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickIntent.setType("image/*");
        intentList = addIntentsToList(context, intentList, pickIntent);
        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    context.getString(R.string.select_source));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        return chooserIntent;
    }

    public static Intent getPDFShareIntent(Context context, Uri uri) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        return Intent.createChooser(shareIntent, context.getString(R.string.select_source));
    }

    public static Intent getCameraIntent(Context context) {
        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra("return-data", true);
        File file = ImageUtils.getTempFile(context);
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, UriUtils.getUriFromFile(context, file));
        intentList = addIntentsToList(context, intentList, takePhotoIntent);

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    context.getString(R.string.select_source));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        return chooserIntent;
    }

    public static Intent getPickImageIntent(Context context) {
        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();

        Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickIntent.setType("image/*");
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra("return-data", true);
        File file = ImageUtils.getTempFile(context);
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, UriUtils.getUriFromFile(context, file));
        intentList = addIntentsToList(context, intentList, pickIntent);
        intentList = addIntentsToList(context, intentList, takePhotoIntent);

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    context.getString(R.string.select_source));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        return chooserIntent;
    }

    private static List<Intent> addIntentsToList(Context context, List<Intent> list, Intent intent) {
        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
        }
        return list;
    }

}
