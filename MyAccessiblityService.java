package com.example.accessblityexample;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class MyAccessibilityService extends AccessibilityService {

    private static final String TAG = "MyAccessibilityService";
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.e(TAG, "onAccessibilityEvent: " );

        String packageName = accessibilityEvent.getPackageName().toString();
        PackageManager packageManager = this.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName,0);
            CharSequence applicationLabel = packageManager.getApplicationLabel(applicationInfo);
            Log.e(TAG, "app name is"+applicationLabel );
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onInterrupt() {
        Log.e(TAG, "onInterrupt: something went wrong" );

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED |
                AccessibilityEvent.TYPE_VIEW_FOCUSED;




        // Set the type of feedback your service will provide.
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;

       

        info.notificationTimeout = 100;

        this.setServiceInfo(info);
        Log.e(TAG, "onServiceConnected: " );



    }
}
