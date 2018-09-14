package models

import java.util.UUID

case class Application(name:String, appCategory: UUID, id:Long, description: String, rating: Int, size: Double, image: String, longDescription: String)
