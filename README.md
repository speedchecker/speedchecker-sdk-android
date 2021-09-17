# Speedchecker SDK for Android - Free speed test for your own app.

Speedchecker SDK for Android allows developers to integrate speed test features into their own apps. You can also try our app on [Google Play](https://play.google.com/store/apps/details?id=uk.co.broadbandspeedchecker&hl=en_US), it's powered by the latest Speedchecker SDK version. More information about [SpeedChecker SDKs](https://www.speedchecker.com/speed-test-tools/mobile-apps-and-sdks.html)

## Features

* latency, download and upload speed of the user connection
* robust measuring of cellular, wireless, even local network
* testing details like the current speed and progress
* additional information like network type and location \(see KPI list below in FAQ\)
* included high-capacity servers provided and maintained by [Speedchecker](https://www.speedchecker.com) or custom servers
* detailed statistics and reports by Speedchecker

## Requirements

* minSdkVersion 16
* compileSdkVersion 30
* Location permissions

## Installation

**1. Update the "project level" gradle file.**

{% tabs %}
{% tab title="Java" %}
```java
 allprojects { repositories { maven { url 'https://maven.speedcheckerapi.com/artifactory/libs-demo' credentials { username = "demo" password = "AP85qiz6wYEsCttWU2ZckEWSwJKuA6mSYcizEY" } } } } 
 implementation 'com.speedchecker:android-sdk:4.2.118-demo-2' 
 public void onCreate() { super.onCreate(); SpeedcheckerSDK.init(this); } 
 SpeedcheckerSDK.askPermissions(this); 
```
{% endtab %}
{% endtabs %}

 import com.speedchecker.android.sdk.Public.SpeedTestListener;

public class SpeedTestCallbacks implements SpeedTestListener { @Override public void onTestStarted\(\) { //your code here } @Override public void onFetchServerFailed\(\) { //your code here } //… other methods here } &lt;/pre&gt; **6. Set implemented interface.**

## License

```java
 SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(new SpeedTestCallbacks()); OR SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(this); 
 SpeedcheckerSDK.SpeedTest.startTest(this); 
```

SpeedChecker is offering different types of licenses

| Free | Basic | Advanced |
| :--- | :--- | :--- |
| Download / Upload / Latency | Download / Upload / Latency | Download / Upload / Latency |
| Device / Network KPIs | Device / Network KPIs | Device / Network KPIs |
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

Free version of the SDK allows to get basic metrics which are described in this [API documentation](https://github.com/speedchecker/speedchecker-sdk-android/wiki/API-documentation)

[Full list of our KPIs of Basic and Advanced versions](https://www.speedchecker.com/broadband-data/kpis.html)

**Do you host all infrastructure for the test?**

Yes, you do not need to run any servers. We provide and maintain network of high-quality servers and CDNs to ensure the testing is accurate. If you wish to configure your own server, this is possible on Basic and Advanced plans.

**How do you measure the speed?**

See our [measurement methodology](https://www.speedchecker.com/broadband-data/measurement-method.html)

## What's next?

Please contact us for more details and license requirements. Also you can download the latest framework version, the sample app to see detailed implementation in Xcode project as well as our Internet Speed Test application on App Store.

* [More information about SpeedChecker SDKs](https://www.speedchecker.com/speed-test-tools/mobile-apps-and-sdks.html)
* [API documentation](https://github.com/speedchecker/speedchecker-sdk-android/wiki/API-documentation)
* [Buy license](https://www.speedchecker.com/contact-us.html)
* [Contact us](https://www.speedchecker.com/contact-us.html)
* [Demo app](https://github.com/speedchecker/speedchecker-sdk-android/tree/demo-app)

