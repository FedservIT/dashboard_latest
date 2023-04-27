package com.fedserv.pulse_dashboard.service;
import com.fedserv.pulse_dashboard.model.Notification;
import okhttp3.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@Component
public class NotificationService {

    public String broadcast(String title, String message) {

        try {

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n \"to\" : \"/topics/broadcastall\",\n \"notification\" : {\n \"title\" : \""+title+"\",\n \"body\" : \""+message+"\"\n },\n \"data\" : {\n     \"body\" : \"Body of Your Notification in Data\",\n     \"title\": \"Title of Your Notification in Title\",\n     \"key_1\" : \"Value for key_1\",\n     \"key_2\" : \"Value for key_2\"\n }\n}\n");
            Request request = new Request.Builder()
                    .url("https://fcm.googleapis.com/fcm/send")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("authorization", "key=AAAANro2sZQ:APA91bGsIpLayhm1NCVg338vok13PZNkRn6QeIvqcTTMmhKVmYvkz8O5r7rJzxAao_9lSJBTFjo5eG48MhDwN8WHvxQbA7H53GuLK4clR868a5FeGKXPe0g0WhiVkLPpNsQ9-XAozhPN")
                    .addHeader("cache-control", "no-cache")
                    .build();

            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "completed";
    }
}

