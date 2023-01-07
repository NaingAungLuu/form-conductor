package me.naingaungluu.formconductor

interface CollectableFormData<T: Any> {
    fun collectFormData(): T
}