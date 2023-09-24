package com.delalic.todolistapp.util

object Constants {
    const val DATABASE_TABLE = "todo_table"
    const val DATABASE_NAME = "todo_database"

    const val LIST_SCREEN = "list/{action}"
    const val TASK_SCREEN = "task/{taskId}"

    const val LIST_ARGUMENT_KEY = "action"
    const val TASK_ARGUMENT_KEY = "taskId"

    const val MAX_TITLE_LENGTH = 20

    //    preference_name references to whole repository, and inside it there is sorting preference saved in a map
    //    e.g. getPreferences(PREFERENCE_NAME)[PREFERENCE_KEY] = some_priority
    const val PREFERENCE_NAME = "todo_preferences"
    const val PREFERENCE_KEY = "sort_state"
}