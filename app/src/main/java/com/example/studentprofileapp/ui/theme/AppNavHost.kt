package com.example.studentprofileapp.ui.theme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.studentprofileapp.data.StudentRepository
import com.example.studentprofileapp.navigation.NavRoutes
import com.example.studentprofileapp.ui.theme.screens.StudentDetailScreen
import com.example.studentprofileapp.ui.theme.screens.StudentListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.StudentList) {
        composable(NavRoutes.StudentList) {
            StudentListScreen(
                students = StudentRepository.students,
                onStudentClick = { id -> navController.navigate("${NavRoutes.StudentDetail}/$id") }
            )
        }

        composable(
            route = NavRoutes.StudentDetailWithArg,
            arguments = listOf(navArgument("studentId") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt("studentId")
            val student = id?.let { StudentRepository.findById(it) }

            StudentDetailScreen(student = student, onBack = { navController.popBackStack() })
        }
    }
}
