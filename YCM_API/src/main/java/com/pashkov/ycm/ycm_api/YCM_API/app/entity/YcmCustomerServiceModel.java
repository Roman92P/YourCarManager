package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.resource
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "YcmCustomerService")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YcmCustomerServiceModel  extends RepresentationModel<YcmCustomerServiceModel> {

    @JsonProperty
    private ServiceEnum serviceType;

    @JsonProperty
    private String serviceDescription;

    @JsonProperty
    private String servicePrice;

    @JsonProperty
    private YcmCurrency currency;

    @JsonProperty
    private String serviceAppointmentDay;

    @JsonProperty
    private String serviceHour;

    @JsonProperty
    private YcmCustomerModel ycmCustomerPurchaseService;

}
