package com.example.recycleview.model

data class Student(
    val id: String,          // Khóa chính định danh duy nhất (UUID)[cite: 9]
    val mssv: String,        // Mã số sinh viên UTE[cite: 9]
    val name: String,        // Họ và tên[cite: 9]
    val gpa: Double,         // Điểm tích lũy hệ 4.0[cite: 9]
    val major: String,       // Chuyên ngành đào tạo[cite: 9]
    val avatarColor: Int     // Mã màu hiển thị trên avatar[cite: 9]
)