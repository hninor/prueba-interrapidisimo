package com.hninor.pruebainterrapidisimo.core

class ApiException(val customMessage: String, val e: Exception) : Exception() {

}
