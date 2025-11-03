package com.example.studentprofileapp.data

import com.example.studentprofileapp.model.Student

object StudentRepository {

    val students = listOf(
        Student(1, "Anna Patel", "Computer Science", "Curious coder, loves mobile UI and coffee.", "avatar_anna"),
        Student(2, "Ben Johnson", "Information Systems", "Database enthusiast and part-time guitarist.", "avatar_ben"),
        Student(3, "Carla Mendes", "Software Engineering", "Frontend dev and sketchbook hoarder.", "avatar_carla"),
        Student(4, "David Kim", "Cybersecurity", "Loves puzzles and building secure apps.", "avatar_david"),
        Student(5, "Ella Thompson", "Data Science", "Data wrangler who makes charts sing.", "avatar_ella")
    )

    fun findById(id: Int): Student? = students.firstOrNull { it.id == id }
}
