package com.dorothy.myappwork.data.repository

import android.net.http.HttpResponseCache.install
import com.dorothy.myappwork.data.models.Todo
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.Storage
import java.util.Locale.filter

class TodoRepository: TodoService {
    val supabase = createSupabaseClient(

    supabaseUrl = "https://jnauygiimqrlyilkougc.supabase.co/rest/v1/",

    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpuYXV5Z2lpbXFybHlpbGtvdWdjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzczMzgzNTksImV4cCI6MjA5MjkxNDM1OX0.sDgeYTFiDEimahf_zGUDilhoYhw_Id846hNiJS6bz6k"

) {

    install(Auth)

    install(Postgrest)

    install(Storage)

}

    override suspend fun createTask(todo: Todo): Todo? {

        val task = supabase.from("tasks").select().decodeSingle<Todo>()

        return task

    }

    override suspend fun getAllTasks(): List<Todo> {

        val task = supabase.from("tasks").select().decodeList<Todo>()

        return task

    }

    override suspend fun getTask(id: Int): Todo? {

        val todo = supabase.from("tasks").select() {

            filter {

                Todo::id eq id

            }

        }.decodeAsOrNull<Todo>()

        return todo

    }

    override suspend fun updateTask(todo: Todo): Todo? {

        val todo = supabase.from("tasks").update(

            todo

        ) {

            select()

            filter {

                eq("id", todo.id!!)

            }

        }.decodeSingle<Todo>()

        return todo

    }

    override suspend fun deleteTask(id: Int): Boolean {

        supabase.from("cities").delete {

            filter {

                eq("id", id)

            }

        }

        return getTask(id) == null

    }

}


