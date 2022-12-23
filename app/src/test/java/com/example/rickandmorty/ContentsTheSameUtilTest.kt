package com.example.rickandmorty

import com.example.rickandmorty.ui.characters.api.CharacterApi
import com.example.rickandmorty.ui.characters.api.CharacterLocationApi
import com.example.rickandmorty.ui.characters.api.CharacterOriginApi
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ContentsTheSameUtilTest {

    @Test
    fun `(oldItem-id == newItem-id)&&(oldItem-name == newItem-name)&&(---) returns true` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            )
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `oldItem-id != newItem-id returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `oldItem-name != newItem-name returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `oldItem-status != newItem-status returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `oldItem-species != newItem-species returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `oldItem-type != newItem-type returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `oldItem-gender != newItem-gender returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `oldItem-origin != newItem-origin returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `oldItem-location != newItem-location returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `oldItem-image != newItem-image returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `oldItem-episode != newItem-episode returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `oldItem-url != newItem-url returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `oldItem-created != newItem-created returns false` () {
        val result = ContentsTheSameUtil.validateAreContentsTheSame(
            oldItem = CharacterApi(
                2,
                "Morty Smith",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                CharacterLocationApi("Earth", "https://rickandmortyapi.com/api/location/20"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z"
            ),
            newItem = CharacterApi(
                14,
                "Alien Morty",
                "unknown",
                "Alien",
                "",
                "Male",
                CharacterOriginApi("unknown", ""),
                CharacterLocationApi("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                listOf<String>("https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/14"),
                "https://rickandmortyapi.com/api/character/14",
                "2017-11-04T20:51:31.373Z"
            )
        )
        assertThat(result).isFalse()
    }

}