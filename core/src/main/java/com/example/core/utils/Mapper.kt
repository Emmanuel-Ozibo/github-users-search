package com.example.core.utils

interface Mapper<INPUT, OUTPUT> {
    fun from(output: OUTPUT): INPUT

    fun to(input: INPUT): OUTPUT
}
