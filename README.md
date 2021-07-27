# Speedchecker SDK - Demo app.

![](https://github.com/speedchecker/speedchecker-sdk-android/blob/master/device-2020-11-10-183218.png)

## Installation

**1. Update the "project level" gradle file.**

```
 allprojects { repositories { maven { url 'https://maven.speedcheckerapi.com/artifactory/libs-demo' credentials { username = "demo" password = "AP85qiz6wYEsCttWU2ZckEWSwJKuA6mSYcizEY" } } } } 
 implementation 'com.speedchecker:android-sdk:4.2.118-demo-2' 
 public void onCreate() { super.onCreate(); SpeedcheckerSDK.init(this); } 
 SpeedcheckerSDK.askPermissions(this); 
```

 import com.speedchecker.android.sdk.Public.SpeedTestListener;

public class SpeedTestCallbacks implements SpeedTestListener { @Override public void onTestStarted\(\) { //your code here } @Override public void onFetchServerFailed\(\) { //your code here } //… other methods here } &lt;/pre&gt; **6. Set implemented interface.**

```
 SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(new SpeedTestCallbacks()); OR SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(this); 
 SpeedcheckerSDK.SpeedTest.startTest(this); 
```

