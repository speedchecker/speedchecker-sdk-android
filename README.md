# Speedchecker SDK for Android - Free speed test for your own app.

Speedchecker SDK for Android allows developers to integrate speed test feature into their own apps. You can also try our app on  [Google Play](https://play.google.com/store/apps/details?id=uk.co.broadbandspeedchecker&hl=en_US), it's powered by the latest Speedchecker SDK version. More information about [SpeedChecker SDKs](https://www.speedchecker.com/speed-test-tools/mobile-apps-and-sdks.html)

## Features
- latency, download and upload speed of the user connection
- robust measuring of cellular, wireless, even local network
- testing details like the current speed and progress
- additional information like network type and location (see KPI list below in FAQ)
- included high-capacity servers provided and maintained by [Speedchecker](https://www.speedchecker.com) or custom servers
- detailed statistics and reports by Speedchecker

## Requirements
 - minSdkVersion 16
 - Location permissions

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
implementation 'com.speedchecker:android-sdk:4.2.105-3-demo'
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
**8. That's it! To get more information you can take a look at our ["Demo" app](https://github.com/speedchecker/speedchecker-sdk-android/tree/master).**

## Licence 
SpeedChecker is offering different types of licences

| Free  | Basic  | Advanced |
| ------------- | ------------- | ------------- |
| Download / Upload / Latency  | Download / Upload / Latency  | Download / Upload / Latency  |
| Device / Network KPIs  | Device / Network KPIs  | Device / Network KPIs  |
| Required location permissions | - | - |
| Required data sharing | - | - |
| - | Custom measurement servers | Custom measurement servers |
| - | - | Background data collection |
| Cost: FREE | Cost: 1000 EUR per app per year | Cost: [Enquire](https://www.speedchecker.com/contact-us.html) |

## FAQ

**Is the SDK free to use?**

Yes! But the SDK collects data on network performance from your app and shares it with Speedchecker and our clients. Free SDK version requires enabled location. Those restrictions are not in Basic and Advanced versions

**Do you have iOS SDK?**

Yes! Please take a look at this [repo](https://github.com/speedchecker/speedchecker-sdk-ios)

**Do you provide free support?**

No, we provide support only on Basic and Advanced plans

**What are all the metrics or KPIs that you can get using our SDKs?**

[Full list of our KPIs](https://www.speedchecker.com/broadband-data/kpis.html)

**Do you host all infrastructure for the test?**

Yes, you do not need to run any servers. We provide and maintain network of high quality servers and CDNs to ensure the testing is accurate. If you wish to configure your own server, this is possible on Basic and Advanced plans.

**How do you measure the speed?**

See our [measurement methodology](https://www.speedchecker.com/broadband-data/measurement-method.html)

## What's next?
Please contact us for more details and license requirements. Also you can download the latest framework version, the sample app to see detailed implementation in Xcode project as well as our Internet Speed Test application on App Store.
- [More information about SpeedChecker SDKs](https://www.speedchecker.com/speed-test-tools/mobile-apps-and-sdks.html)
- [Buy licence](https://www.speedchecker.com/contact-us.html)
- [Contact us](https://www.speedchecker.com/contact-us.html)
- [Demo app](https://github.com/speedchecker/speedchecker-sdk-android/tree/master)
