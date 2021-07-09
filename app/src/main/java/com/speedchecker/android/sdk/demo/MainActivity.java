package com.speedchecker.android.sdk.demo;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.speedchecker.android.sdk.Public.SpeedTestListener;
import com.speedchecker.android.sdk.Public.SpeedTestResult;
import com.speedchecker.android.sdk.SpeedcheckerSDK;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SpeedTestListener {

    private TextView mTextViewStage;
    private TextView mTextViewResult;
    private TextView mTextViewLog;
    private Button mButtonSpeedTest;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewStage = findViewById(R.id.textView_stage);
        mTextViewResult = findViewById(R.id.textView_result);
        mTextViewLog = findViewById(R.id.textView_log);
        mButtonSpeedTest = findViewById(R.id.button_speedTest);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());
        mTextViewLog.setMovementMethod(new ScrollingMovementMethod());

        //TODO: 3. Initialize SpeedcheckerSDK inside a main activity.
        SpeedcheckerSDK.init(this);

        //TODO: 4. Ask Location permissions.
        SpeedcheckerSDK.askPermissions(this);
        //OR request location permissions manually. We need:
        //ACCESS_COARSE_LOCATION
        //ACCESS_FINE_LOCATION
        //ACCESS_BACKGROUND_LOCATION

        //TODO: 6. Set implemented interface.
        SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(this);

        mButtonSpeedTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: 7. Start Speed test.
                SpeedcheckerSDK.SpeedTest.startTest(MainActivity.this);
            }
        });

        //===========================
        //=== Additional features ===
        //===========================
        //NOTE: some features could not be available in some SDK versions.
        // Please, contact us to get more information

        //# Interrupt speed test
        //SpeedcheckerSDK.SpeedTest.interruptTest();

        //# Register listener to get additional information (IP, ISP).
        // This callback will fire after internet speed test
        //SpeedcheckerSDK.SpeedTest.setAfterTestUserInfoListener(new SpeedTestListener.AfterTestUserInfo() {
        //    @Override
        //    public void getUserInfoCallback(String IP, String ISP) {
        //
        //    }
        //});
    }

    //TODO: 5. Implement the "SpeedTestListener" interface and override methods.
    @Override
    public void onTestStarted() {
        mTextViewStage.setText("Test Started");
        mTextViewResult.setText("-");
        log("Test Started");
    }

    @Override
    public void onFetchServerFailed() {
        mTextViewStage.setText("Fetch Server Failed");
        mTextViewResult.setText("-");
        log("Fetch Server Failed");
    }

    @Override
    public void onFindingBestServerStarted() {
        mTextViewStage.setText("Finding best server");
        mTextViewResult.setText("...");
        log("Finding best server");
    }

    @Override
    public void onTestFinished(SpeedTestResult speedTestResult) {
        String finalStr =
                "Server: " + speedTestResult.getServer().Domain + "\n"
                        + "Ping: " + speedTestResult.getPing() + " ms" + "\n"
                        + "Download speed: " + speedTestResult.getDownloadSpeed() + " Mb/s" + "\n"
                        + "Upload speed: " + speedTestResult.getUploadSpeed() + " Mb/s" + "\n"
                        + "Connection type: " + speedTestResult.getConnectionTypeHuman() + "\n";
        mTextViewStage.setText("Test Finished");
        mTextViewResult.setText(finalStr);
        log("Test Finished: Server[" + speedTestResult.getServer().Domain + "] -> " + speedTestResult.toString());
    }

    @Override
    public void onPingStarted() {
        mTextViewStage.setText("Ping Started");
        mTextViewResult.setText("...");
        log("Ping Started");
    }

    @Override
    public void onPingFinished(int ping, int jitter) {
        mTextViewStage.setText("Ping Finished");
        mTextViewResult.setText(ping + " ms | jitter: " + jitter);
        log("Ping Finished: " + ping + " ms| jitter: " + jitter);
    }

    @Override
    public void onDownloadTestStarted() {
        mTextViewStage.setText("Download Test Started");
        mTextViewResult.setText("...");
        log("Download Test Started");
    }

    @Override
    public void onDownloadTestProgress(int i, double speedMbs, double transferredMb) {
        mTextViewStage.setText("Download Test Progress");
        mTextViewResult.setText(i + "% -> " + speedMbs + " Mb/s\nTransferredMb: " + transferredMb);
        log("Download Test Progress: " + i + "% -> " + speedMbs + " Mb/s\nTransferredMb: " + transferredMb);
    }

    @Override
    public void onDownloadTestFinished(double v) {
        mTextViewStage.setText("Download Test Finished");
        mTextViewResult.setText(v + " Mb/s");
        log("Download Test Finished: " + v + " Mb/s");
    }

    @Override
    public void onUploadTestStarted() {
        mTextViewStage.setText("Upload Test Started");
        mTextViewResult.setText("...");
        log("Upload Test Started");
    }

    @Override
    public void onUploadTestProgress(int i, double speedMbs, double transferredMb) {
        mTextViewStage.setText("Upload Test Progress");
        mTextViewResult.setText(i + "% -> " + speedMbs + " Mb/s\nTransferredMb: " + transferredMb);
        log("Upload Test Progress: " + i + "% -> " + speedMbs + " Mb/s\nTransferredMb: " + transferredMb);
    }

    @Override
    public void onUploadTestFinished(double v) {
        mTextViewStage.setText("Upload Test Finished");
        mTextViewResult.setText(v + " Mb/s");
        log("Upload Test Finished: " + v + " Mb/s");
    }

    @Override
    public void onTestWarning(String s) {
        mTextViewStage.setText("Test Warning");
        mTextViewResult.setText(s);
        log("Test Warning: " + s);
    }

    @Override
    public void onTestFatalError(String s) {
        mTextViewStage.setText("Test Fatal Error");
        mTextViewResult.setText(s);
        log("Test Fatal Error: " + s);
    }

    @Override
    public void onTestInterrupted(String s) {
        mTextViewStage.setText("Test Interrupted");
        mTextViewResult.setText(s);
        log("Test Interrupted: " + s);
    }

    private void log(String message){
        mTextViewLog.setText("[" + sdf.format(new Date(System.currentTimeMillis())) + "]: "
                + message + "\n" + mTextViewLog.getText());
    }
}
