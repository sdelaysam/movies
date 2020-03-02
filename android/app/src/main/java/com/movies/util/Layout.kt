package com.movies.util

interface Layout {
    val layoutId: Int
}

interface IdentifiableLayout : Layout, Identifiable