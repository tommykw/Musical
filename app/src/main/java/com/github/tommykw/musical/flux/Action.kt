package com.github.tommykw.musical.flux

interface Action<out T> {
    val data: T
}