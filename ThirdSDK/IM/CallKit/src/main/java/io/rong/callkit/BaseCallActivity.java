package io.rong.callkit;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import io.rong.calllib.IRongCallListener;
import io.rong.calllib.RongCallCommon;
import io.rong.calllib.RongCallSession;
import io.rong.common.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.manager.AudioPlayManager;
import io.rong.imkit.manager.AudioRecordManager;
import io.rong.imkit.utilities.PermissionCheckUtil;
import io.rong.imkit.utils.NotificationUtil;

/**
 * Created by weiqinxiao on 16/3/9.
 */
public class BaseCallActivity extends Activity implements IRongCallListener, PickupDetector.PickupDetectListener {

    private static final String TAG = "BaseCallActivity";
    private final static long DELAY_TIME = 1000;
    static final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 100;
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

    private MediaPlayer mMediaPlayer;
    private Vibrator mVibrator;
    private int time = 0;
    private Runnable updateTimeRunnable;
    private boolean shouldShowFloat;
    private boolean shouldRestoreFloat;
    protected Handler handler;
    private BroadcastReceiver mHomeKeyReceiver;
    protected boolean isFinishing;

    protected PickupDetector pickupDetector;
    protected PowerManager powerManager;
    protected PowerManager.WakeLock wakeLock;

    static final String[] VIDEO_CALL_PERMISSIONS = {Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};
    static final String[] AUDIO_CALL_PERMISSIONS = {Manifest.permission.RECORD_AUDIO};

    public static final int CALL_NOTIFICATION_ID = 4000;

    public void setShouldShowFloat(boolean shouldShowFloat) {
        this.shouldShowFloat = shouldShowFloat;
    }

    public void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void postRunnableDelay(Runnable runnable) {
        handler.postDelayed(runnable, DELAY_TIME);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RLog.d(TAG, "BaseCallActivity onCreate");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        shouldRestoreFloat = true;

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = pm.isScreenOn();
        if (!isScreenOn) {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
            wl.acquire();
            wl.release();
        }
        handler = new Handler();
        mHomeKeyReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                    String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                    if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason) && shouldShowFloat) {
                        finish();
                    }
                }
            }
        };
        try {
            registerReceiver(mHomeKeyReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        isFinishing = false;
        RongCallProxy.getInstance().setCallListener(this);

        AudioPlayManager.getInstance().stopPlay();
        AudioRecordManager.getInstance().destroyRecord();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("floatbox");
        if (shouldRestoreFloat && bundle != null) {
            onRestoreFloatBox(bundle);
        }
    }

    public void onOutgoingCallRinging() {
        mMediaPlayer = MediaPlayer.create(this, R.raw.voip_outgoing_ring);

        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }

    public void onIncomingCallRinging() {
        int ringerMode = NotificationUtil.getRingerMode(this);
        if (ringerMode != AudioManager.RINGER_MODE_SILENT) {
            if (ringerMode == AudioManager.RINGER_MODE_VIBRATE) {
                mVibrator = (Vibrator) RongContext.getInstance().getSystemService(Context.VIBRATOR_SERVICE);
                mVibrator.vibrate(new long[]{500, 1000}, 0);
            } else {
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                mMediaPlayer = new MediaPlayer();
                try {
                    mMediaPlayer.setDataSource(this, uri);
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setupTime(final TextView timeView) {
        if (updateTimeRunnable != null) {
            handler.removeCallbacks(updateTimeRunnable);
        }
        updateTimeRunnable = new UpdateTimeRunnable(timeView);
        handler.post(updateTimeRunnable);
    }

    public int getTime() {
        return time;
    }

    public void stopRing() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer = null;
        }
        if (mVibrator != null) {
            mVibrator.cancel();
            mVibrator = null;
        }
    }

    @Override
    public void onCallOutgoing(RongCallSession callProfile, SurfaceView localVideo) {
    }

    @Override
    public void onRemoteUserRinging(String userId) {

    }

    @Override
    public void onCallDisconnected(RongCallSession callProfile, RongCallCommon.CallDisconnectedReason reason) {
        shouldShowFloat = false;

        String text = null;
        switch (reason) {
            case CANCEL:
                text = getString(R.string.rc_voip_mo_cancel);
                break;
            case REJECT:
                text = getString(R.string.rc_voip_mo_reject);
                break;
            case NO_RESPONSE:
            case BUSY_LINE:
                text = getString(R.string.rc_voip_mo_no_response);
                break;
            case REMOTE_BUSY_LINE:
                text = getString(R.string.rc_voip_mt_busy);
                break;
            case REMOTE_CANCEL:
                text = getString(R.string.rc_voip_mt_cancel);
                break;
            case REMOTE_REJECT:
                text = getString(R.string.rc_voip_mt_reject);
                break;
            case REMOTE_NO_RESPONSE:
                text = getString(R.string.rc_voip_mt_no_response);
                break;
            case REMOTE_HANGUP:
            case HANGUP:
            case NETWORK_ERROR:
            case INIT_VIDEO_ERROR:
                text = getString(R.string.rc_voip_call_terminalted);
                break;
        }
        if (text != null) {
            showShortToast(text);
        }
        stopRing();
        NotificationUtil.clearNotification(this, BaseCallActivity.CALL_NOTIFICATION_ID);
        RongCallProxy.getInstance().setCallListener(null);
    }

    @Override
    public void onRemoteUserInvited(String userId, RongCallCommon.CallMediaType mediaType) {

    }

    @Override
    public void onRemoteUserJoined(String userId, RongCallCommon.CallMediaType mediaType, SurfaceView remoteVideo) {

    }

    @Override
    public void onRemoteUserLeft(String userId, RongCallCommon.CallDisconnectedReason reason) {

    }

    @Override
    public void onMediaTypeChanged(String userId, RongCallCommon.CallMediaType mediaType, SurfaceView video) {

    }

    @Override
    public void onError(RongCallCommon.CallErrorCode errorCode) {
    }

    @Override
    public void onCallConnected(RongCallSession callProfile, SurfaceView localVideo) {
        shouldShowFloat = true;
    }


    @Override
    protected void onPause() {
        isFinishing = isFinishing();
        if (isFinishing) {
            try {
                if (mHomeKeyReceiver != null) {
                    unregisterReceiver(mHomeKeyReceiver);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (shouldShowFloat) {
                Bundle bundle = new Bundle();
                String action = onSaveFloatBoxState(bundle);
                if (action != null) {
                    bundle.putString("action", action);
                    CallFloatBoxView.showFloatBox(getApplicationContext(), bundle, time);
                    int mediaType = bundle.getInt("mediaType");
                    showOnGoingNotification(getString(R.string.rc_call_on_going),
                            mediaType == RongCallCommon.CallMediaType.AUDIO.getValue() ? getString(R.string.rc_audio_call_on_going) : getString(R.string.rc_video_call_on_going));
                }
            }
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        RLog.d(TAG, "BaseCallActivity onResume");
        RongCallProxy.getInstance().setCallListener(this);
        if (shouldRestoreFloat) {
            time = CallFloatBoxView.hideFloatBox();
        }
        shouldRestoreFloat = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        shouldRestoreFloat = false;
    }

    @Override
    protected void onDestroy() {
        isFinishing = false;
        handler.removeCallbacks(updateTimeRunnable);
        super.onDestroy();
    }

    @Override
    public void onRemoteCameraDisabled(String userId, boolean muted) {

    }

    public void onRestoreFloatBox(Bundle bundle) {

    }

    public String onSaveFloatBoxState(Bundle bundle) {
        return null;
    }

    public void showOnGoingNotification(String title, String content) {
        Intent intent = new Intent(getIntent().getAction());
        Bundle bundle = new Bundle();
        onSaveFloatBoxState(bundle);
        intent.putExtra("floatbox", bundle);
        intent.putExtra("callAction", RongCallAction.ACTION_RESUME_CALL.getName());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationUtil.showNotification(this, title, content, pendingIntent, CALL_NOTIFICATION_ID, Notification.DEFAULT_LIGHTS);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @TargetApi(23)
    boolean requestCallPermissions(RongCallCommon.CallMediaType type, int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        String[] permissions;
        if (type.equals(RongCallCommon.CallMediaType.VIDEO)) {
            permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        } else if (type.equals(RongCallCommon.CallMediaType.AUDIO)) {
            permissions = new String[]{Manifest.permission.RECORD_AUDIO};
        } else {
            return true;
        }
        return PermissionCheckUtil.requestPermissions(this, permissions, requestCode);
    }

    private class UpdateTimeRunnable implements Runnable {
        private TextView timeView;

        public UpdateTimeRunnable(TextView timeView) {
            this.timeView = timeView;
        }

        @Override
        public void run() {
            time++;
            if (time >= 3600) {
                timeView.setText(String.format("%d:%02d:%02d", time / 3600, (time % 3600) / 60, (time % 60)));
            } else {
                timeView.setText(String.format("%02d:%02d", (time % 3600) / 60, (time % 60)));
            }
            handler.postDelayed(this, 1000);
        }
    }

    void onMinimizeClick(View view) {
        if (Build.BRAND.toLowerCase().contains("xiaomi")) {
            if (PermissionCheckUtil.canDrawOverlays(this)) {
                finish();
            } else {
                Toast.makeText(this, R.string.rc_voip_float_window_not_allowed, Toast.LENGTH_LONG).show();
            }
        } else {
            finish();
        }
    }

    protected void createPowerManager() {
        if (powerManager == null) {
            powerManager = (PowerManager) getSystemService(POWER_SERVICE);
            wakeLock = powerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, TAG);
        }
    }

    protected void createPickupDetector() {
        if (pickupDetector == null) {
            pickupDetector = new PickupDetector(this);
        }
    }

    @Override
    public void onPickupDetected(boolean isPickingUp) {
        if (wakeLock == null) {
            RLog.d(TAG, "No PROXIMITY_SCREEN_OFF_WAKE_LOCK");
            return;
        }
        if (isPickingUp && !wakeLock.isHeld()) {
            setShouldShowFloat(false);
            shouldRestoreFloat = false;
            wakeLock.acquire();
        }
        if (!isPickingUp && wakeLock.isHeld()) {
            try {
                wakeLock.setReferenceCounted(false);
                wakeLock.release();
                setShouldShowFloat(true);
                shouldRestoreFloat = true;
            } catch (Exception e) {

            }
        }
    }
}
