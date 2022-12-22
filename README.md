---
description: Developers guide
---

# SpeedChecker SDK for Android

## Free speed test features for your own app

SpeedChecker SDK for Android allows developers to integrate speed test features into their own apps. You can also try our app on [Google Play](https://play.google.com/store/apps/details?id=uk.co.broadbandspeedchecker\&hl=en\_US), it's powered by the latest Speedchecker SDK version. More information about [SpeedChecker SDKs](https://www.speedchecker.com/speed-test-tools/mobile-apps-and-sdks.html)

## Features

* latency, download and upload speed of the user connection
* robust measuring of cellular, wireless and even local network
* testing details like the current speed and progress
* additional information like network type and location (see KPI list below in FAQ)
* included high-capacity servers provided and maintained by [Speedchecker](https://www.speedchecker.com) or custom servers
* detailed statistics and reports by Speedchecker

## Requirements

* minSdkVersion 19
* compileSdkVersion 31
* Location permissions

## Installation

### **1. Update the "project level" gradle file.**

```java
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
```

### **2. Update the "app level" gradle file.**

```java
implementation 'com.speedchecker:android-sdk:4.2.168-android-12-demo'
```

### **3.** Initialize SpeedcheckerSDK inside the main activity.

```java
public void onCreate() {
  super.onCreate();
  SpeedcheckerSDK.init(this);
}
```

### **4. Ask for Location permissions.**

```java
SpeedcheckerSDK.askPermissions(this);
```

### 5. Implement the "SpeedTestListener" interface and override methods

An example below shows a new class with overriding methods but you could implement the interface at "MainActivity" or somewhere else.

```java
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
```

### **6. Set implemented interface.**

```java
SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(new SpeedTestCallbacks());
OR
SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(this);
```

### **7. Start Speed test.**

```java
SpeedcheckerSDK.SpeedTest.startTest(this);
```

### 8. That's it! To get more information you can take a look at our [Demo App](https://github.com/speedchecker/speedchecker-sdk-android/tree/demo-app)

## License

SpeedChecker is offering different types of licenses:

| Items                             | Free                          | Basic                                             | Advanced                                                          |
| --------------------------------- | ----------------------------- | ------------------------------------------------- | ----------------------------------------------------------------- |
| Speed Test Metrics                | Download / Upload / Latency   | Download / Upload / Latency / Jitter              | Download / Upload / Latency / Jitter                              |
| Accompanying Metrics              | Device / Network KPIs         | Device / Network KPIs                             | Device / Network KPIs / Advanced Cellular KPIs                    |
| Test Customization                | -                             | test duration, multi-threading, warm-up phase etc | test duration, multi-threading, warm-up phase etc                 |
| Location Permission               | Required location permissions | -                                                 | -                                                                 |
| Data Sharing Requirement          | Required data sharing         | -                                                 | -                                                                 |
| Measurement Servers               | -                             | Custom measurement servers                        | Custom measurement servers                                        |
| Background and passive collection | -                             | -                                                 | Background and Passive data collection                            |
| Cost                              | **FREE**                      | 1,200 EUR per app per year                        | Cost: [**Enquire**](https://www.speedchecker.com/contact-us.html) |

## FAQ

### Is the SDK free to use?

Yes! But the SDK collects data on network performance from your app and shares it with Speedchecker and our clients.The free SDK version requires and enabled location. Those restrictions are not in the Basic and Advanced versions

### **Do you have iOS SDK?**

Yes! Please take a look at this [repo](https://github.com/speedchecker/speedchecker-sdk-ios)

### **Do you provide free support?**

No, we provide support only on Basic and Advanced plans

### **What are all the metrics or KPIs that you can get using our SDKs?**

The free version of the SDK allows getting basic metrics which are described in this [API documentation](https://github.com/speedchecker/speedchecker-sdk-android/wiki/API-documentation)

[Full list of our KPIs for Basic and Advanced versions](https://docs.speedchecker.com/measurement-methodology-links/u21ongNGAYLb6eo7cqjY/kpis-and-measurements/list-of-kpis)

### **Do you host all infrastructure for the test?**

Yes, you do not need to run any servers. We provide and maintain a network of high-quality servers and CDNs to ensure the testing is accurate. If you wish to configure your own server, this is possible on Basic and Advanced plans.

### **How do you measure the speed?**

See our [measurement methodology](https://docs.speedchecker.com/measurement-methodology-links/u21ongNGAYLb6eo7cqjY/kpis-and-measurements/data-collection-methodologies)

## What's next?

Please contact us for more details and license requirements. Also, you can download the latest framework version, the sample app to see detailed implementation in the Xcode project as well as our Internet Speed Test application on App Store.

* [More information about SpeedChecker SDKs](https://www.speedchecker.com/speed-test-tools/mobile-apps-and-sdks.html)
* [API documentation](https://github.com/speedchecker/speedchecker-sdk-android/wiki/API-documentation)
* [Buy license](https://www.speedchecker.com/contact-us.html)
* [Contact us](https://www.speedchecker.com/contact-us.html)
* [Demo app](https://github.com/speedchecker/speedchecker-sdk-android/tree/demo-app)
