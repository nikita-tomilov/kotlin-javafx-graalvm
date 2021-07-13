package com.nikitatomilov.kjg.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import feign.Param
import feign.RequestLine

//example taken from https://github.com/OpenFeign/feign
interface GitHub {
  @RequestLine("GET /repos/{owner}/{repo}/contributors")
  fun contributors(
    @Param("owner") owner: String,
    @Param("repo") repo: String?
  ): List<Contributor>
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class Contributor(
  val login: String,
  val contributions: Int
)