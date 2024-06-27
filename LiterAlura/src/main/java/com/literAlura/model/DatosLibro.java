package com.literAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(@JsonProperty("title") String titulo,
                         @JsonProperty("authors") List<DatosAutor> autor,
                         @JsonProperty("languages") List<String> idioma,
                         @JsonProperty("download_count") int descargas) {
}


