package com._02server;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.google.auth.oauth2.GoogleCredentials.fromStream;

public class BucketService {
    String keyFilePath = "KEY_FILE.json";
    // GCS 클라이언트 초기화
    Storage storage;
    public BucketService() throws IOException {
        storage = StorageOptions.newBuilder()
                .setCredentials(fromStream(new FileInputStream(keyFilePath)))
                .build()
                .getService();
    }
    public boolean load_bucket(String filename) throws IOException {

        // 버킷에서 모든 파일 목록 가져오기
        Iterable<Blob> blobs = storage.list("update-metadata").iterateAll();

        // 각 파일에 대한 처리
        for (Blob blob : blobs) {
            // 파일 이름에서 category 값이 포함되어 있는지 확인
            if (blob.getName().contains(filename)) {
                // 원하는 동작을 수행 (예: 파일 다운로드)
                blob.downloadTo(Paths.get("./"+filename));
                return true;

                // 파일 다운로드 등의 작업을 수행할 수 있습니다.
                // blob.downloadTo(...);
            }
        }
        System.out.println("error! no blob!");
        return false;
    }

    public void save_bucket(String filePath) throws IOException {

        // 서비스 계정에 대한 인증 키 파일 경로
        String keyFilePath = "KEY_FILE.json";

        // GCS 클라이언트 초기화
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(fromStream(new FileInputStream(keyFilePath)))
                .build()
                .getService();

        // BlobId 생성
        BlobId blobId = BlobId.of("update-metadata", filePath);

        // BlobInfo 생성
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        // Blob 생성 및 업로드
        Blob blob = storage.create(blobInfo, filePath.getBytes());

        System.out.println("File uploaded to GCS: " + blob.getName());

    }

    public void delete_bucket(String filename) throws IOException {
        BlobId blobId = BlobId.of("update-metadata", filename);
        boolean deleted = storage.delete(blobId);
    }
}
