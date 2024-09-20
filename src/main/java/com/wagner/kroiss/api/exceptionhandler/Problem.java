package com.wagner.kroiss.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400")
    private Integer status;

    @ApiModelProperty(example = "2024-09-20T19:29:13.880+0000")
    private OffsetDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Field> fields;

    @ApiModel(value = "ObjetoProblema")
    @Getter
    @Builder
    public static class Object {

        private String name;
        private String userMessage;

    }

}
