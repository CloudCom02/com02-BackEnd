package com._02server.com02backendproject.service;

import com._02server.com02backendproject.entities.Device;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceAddService {
    static void saveDataToDB(List<Device> list) {
        String DB_URL = "jdbc:mysql://0.0.0.0:3306/com02?serverTimezone=UTC";
        String DB_USER = "root";
        String DB_PASSWORD = "Cloudcom02!";

        try {
            // 데이터베이스 연결
            Class.forName("com.mysql.Jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Device 객체의 리스트를 반복하면서 데이터베이스에 삽입
            for (Device device : list) {
                insertDevice(connection, device);
            }

            // 연결 종료
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void insertDevice(Connection connection, Device device) throws SQLException {
        String insertQuery = "INSERT INTO device (device_name,category,maximum_output,wattPerhour,entire_capacity,imageURL) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // 각 필드에 대한 값을 설정
            preparedStatement.setString(1, device.getDeviceName());
            preparedStatement.setString(2, device.getCategory());
            preparedStatement.setDouble(3, device.getMaximum_output());
            preparedStatement.setInt(4, device.getWattPerhour());
            preparedStatement.setInt(5, device.getMAh());
            preparedStatement.setString(6, device.getImageURL());
            // ... 필요한 만큼 계속 설정

            // 쿼리 실행
            preparedStatement.executeUpdate();
        }
    }
    static void saveDataToCSV(String name, String contents, String filePath) {
        try (Writer writer = new FileWriter(filePath, true)) {
            List<String[]> data = new ArrayList<>();


            // 데이터 행 추가
            String[] row = {name, contents};
            data.add(row);

            for (String[] rowData : data) {
                writer.write(String.join(" ※ ", rowData));
            }
            writer.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
