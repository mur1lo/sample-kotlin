package io.github.murilo.javalin.sample.resources.extension

import io.javalin.Context

fun Context.paramAsLong(pathParam: String) = this.pathParam(pathParam).toLong()
