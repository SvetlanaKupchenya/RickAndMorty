package com.example.rickandmorty

import com.example.rickandmorty.ui.characters.api.CharacterApi

object ContentsTheSameUtil {

    /*
     * Функция должна возвращать true при совпадении всех полей экземпляра oldItem
     * с полями экземпляра newItem для класса CharacterApi
     * и возвращать false во всех остальных случаях - при любом несовпадении
     * If oldItem.id != newItem.id -> false
     * If oldItem.name != newItem.name -> false
     * ...
     * here all other fields
     * ...
     * ...oldItem.created != newItem.created -> false
     */
    fun validateAreContentsTheSame(
        oldItem: CharacterApi,
        newItem: CharacterApi
    ): Boolean {
        if ((oldItem.id == newItem.id)
                && (oldItem.name == newItem.name)
                && (oldItem.created == newItem.created)
                && (oldItem.status == newItem.status)
                && (oldItem.species == newItem.species)
                && (oldItem.type == newItem.type)
                && (oldItem.gender == newItem.gender)
                && (oldItem.origin == newItem.origin)
                && (oldItem.location == newItem.location)
                && (oldItem.image == newItem.image)
                && (oldItem.episode == newItem.episode)
                && (oldItem.url == newItem.url)
                && (oldItem.created == newItem.created)) {
            return true
        }

        if (oldItem.id != newItem.id) {
            return false
        }

        if (oldItem.name != newItem.name) {
            return false
        }

        if (oldItem.created != newItem.created) {
            return false
        }

        if (oldItem.status != newItem.status) {
            return false
        }

        if (oldItem.species != newItem.species) {
            return false
        }

        if (oldItem.type != newItem.type) {
            return false
        }

        if (oldItem.gender != newItem.gender) {
            return false
        }

        if (oldItem.origin != newItem.origin) {
            return false
        }

        if (oldItem.location != newItem.location) {
            return false
        }

        if (oldItem.image != newItem.image) {
            return false
        }

        if (oldItem.episode != newItem.episode) {
            return false
        }

        if (oldItem.url != newItem.url) {
            return false
        }

        if (oldItem.created != newItem.created) {
            return false
        }

        return oldItem == newItem
    }
}