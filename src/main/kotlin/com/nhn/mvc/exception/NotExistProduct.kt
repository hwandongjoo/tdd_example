package com.nhn.mvc.exception

import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotExistProduct(val errorCode:ProductErrorCode) : RuntimeException()


enum class ProductErrorCode {
    NOT_EXIST,
    ;
}
