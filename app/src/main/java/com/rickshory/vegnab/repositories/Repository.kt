package com.rickshory.vegnab.repositories

interface Specification {} // marker interface

interface Repository<T> {
    fun add(item: T)

    fun add(items: Iterable<T>)

    fun update(item: T)

    fun remove(item: T)

    fun remove(specification: Specification)

    fun query(specification: Specification): List<T>
}