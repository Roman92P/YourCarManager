package com.pashkov.ycm.ycm_api.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Roman Pashkov created on 05.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "YcmCustomer")
@Relation(collectionRelation = "ycmCustomers")
@JsonInclude(Include.NON_NULL)
public class YcmCustomerModel extends RepresentationModel<YcmCustomerModel> {

    @JsonProperty("name")
    private String name;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("nick")
    private String nick;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("role")
    private Role role;


}
