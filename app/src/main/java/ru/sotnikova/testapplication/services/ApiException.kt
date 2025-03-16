package ru.sotnikova.testapplication.services

import java.io.IOException

class ApiException(message: String): IOException(message)