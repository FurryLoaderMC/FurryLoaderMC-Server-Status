package FurryLoaderMC.ServerStatus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Player(
    @SerialName("name") val name: String,
    @SerialName("uuid") val uuid: String
)


@Serializable
data class Players(
    @SerialName("current") val current: Int,
    @SerialName("maximum") val maximum: Int,
    @SerialName("players") val players: List<Player>
)


@Serializable
data class Performance(
    @SerialName("tps") val tps: Double,
    @SerialName("mspt") val mspt: Double,
)
