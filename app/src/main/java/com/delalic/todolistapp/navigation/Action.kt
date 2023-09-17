package com.delalic.todolistapp.navigation

enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}

fun String?.toAction() : Action {
    if (this.isNullOrEmpty()) {
        return Action.NO_ACTION
    }
    return try {

        Action.valueOf(this)
    } catch (e: Throwable) {
        Action.NO_ACTION
    }
}