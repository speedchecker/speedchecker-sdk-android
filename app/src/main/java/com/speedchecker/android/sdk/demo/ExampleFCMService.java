package com.speedchecker.android.sdk.demo;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.speedchecker.android.sdk.SpeedcheckerSDK;

public class ExampleFCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        //Please, insert next lines inside your "onMessageReceived" method
        if(remoteMessage.getFrom() != null
                && remoteMessage.getFrom().contentEquals(SpeedcheckerSDK.Probe.getSenderID())){
            SpeedcheckerSDK.Probe.sendMessage(getApplicationContext(), remoteMessage);
            return;
        }
        //Your code here
    }

}

