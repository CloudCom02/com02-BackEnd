package com._02server;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class BucketService {
//    public static void maindd(String[] args) throws IOException {
//        // 서비스 계정에 대한 인증 키 파일 경로
//        String keyFilePath = "instant-droplet-406401-561aaf7cb06e.json";
//
//        // GCS 클라이언트 초기화
//        Storage storage = StorageOptions.newBuilder()
//                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(keyFilePath)))
//                .build()
//                .getService();
//
//        // 버킷의 객체 목록 가져오기
//        Bucket bucket = storage.get("your-bucket-name");
//        bucket.list().iterateAll().forEach(blob -> {
//            System.out.println("Object Name: " + blob.getName());
//            System.out.println("Size: " + blob.getSize());
//            // 추가적인 작업 수행
//        });
//    }
}
