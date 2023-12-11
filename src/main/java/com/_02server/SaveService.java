package com._02server;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.List;


public class SaveService {
    static String DB_URL = System.getenv("DB_URL");
    static String DB_USER = System.getenv("DB_USERNAME");
    static String DB_PASSWORD = System.getenv("DB_PASSWORD");
    static void saveDataToDB(List<Device> list) {

        try {
            // 데이터베이스 연결
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Device 객체의 리스트를 반복하면서 데이터베이스에 삽입

            insertDevice(connection, list);

            // 연결 종료
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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

    private static void insertDevice(Connection connection, List<Device> devices) throws SQLException {
        String insertQuery = "INSERT INTO device (device_name,category,maximum_output,watt_perhour,entire_capacity,imageurl) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            for(Device device : devices ) {
                // 각 필드에 대한 값을 설정
                preparedStatement.setString(1, device.getName());
                preparedStatement.setString(2, device.getCategory());
                preparedStatement.setDouble(3, device.getMaximum_output());
                preparedStatement.setDouble(4, device.getWattPerhour());
                preparedStatement.setInt(5, device.getmAh());
                preparedStatement.setString(6, device.getImageURL());
                // ... 필요한 만큼 계속 설정

                // 쿼리 실행
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }
    public static HashSet<String> retrieveDeviceNames() {
        String sql = "SELECT device_name FROM device";
        HashSet result = new HashSet();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String deviceName = resultSet.getString("device_name");
                result.add(deviceName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}