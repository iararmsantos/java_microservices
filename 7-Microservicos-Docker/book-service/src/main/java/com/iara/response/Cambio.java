package com.iara.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cambio implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String from;

    private String to;

    private Double conversionFactor;

    private Double convertedValue;

    private String environment;
}
