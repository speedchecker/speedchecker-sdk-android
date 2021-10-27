# Speedchecker SDK - Demo app.
<img src="https://github.com/speedchecker/speedchecker-sdk-android/blob/demo-app/device-2020-11-10-183218.png" width="25%">

## Installation
**1. Update the "project level" gradle file.**
<pre>
allprojects {
   repositories {
       maven {
            url 'https://maven.speedcheckerapi.com/artifactory/libs-demo'
            credentials {
                username = "demo"
                password = "AP85qiz6wYEsCttWU2ZckEWSwJKuA6mSYcizEY"
            }
       }
   }
}
</pre>
**2. Update the "app level" gradle file.**
<pre>
implementation 'com.speedchecker:android-sdk:4.2.129-demo'
</pre>
**3. Initialize SpeedcheckerSDK inside a main activity.**
<pre>
public void onCreate() {
  super.onCreate();
  SpeedcheckerSDK.init(this);
}
</pre>
**4. Ask Location permissions.**
<pre>
SpeedcheckerSDK.askPermissions(this);
</pre>
**5. Implement the "SpeedTestListener" interface and override methods (an example below shows a new class with overriding methods but you could implement the interface at "MainActivity" or somewhere else).**
<pre>
import com.speedchecker.android.sdk.Public.SpeedTestListener;

public class SpeedTestCallbacks implements SpeedTestListener {
   @Override
   public void onTestStarted() {
         //your code here
   }
   @Override
   public void onFetchServerFailed() {
         //your code here
   }
   //… other methods here
}
</pre>
**6. Set implemented interface.**
<pre>
SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(new SpeedTestCallbacks());
OR 
SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(this);
</pre>
**7. Start Speed test.**
<pre>
SpeedcheckerSDK.SpeedTest.startTest(this);
</pre>
**8. That's it. Enjoy!**
