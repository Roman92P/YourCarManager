package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import com.pashkov.ycm.ycm_api.YCM_API.app.resource.YcmUserCustomerResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Roman Pashkov created on 05.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.entity
 */
@Component
public class YcmCustomerRepresentationModelAssembler extends RepresentationModelAssemblerSupport<YcmCustomer, YcmCustomerModel> {

    public YcmCustomerRepresentationModelAssembler() {
        super(YcmUserCustomerResource.class, YcmCustomerModel.class);
    }

    @Override
    public YcmCustomerModel toModel(YcmCustomer entity) {
        YcmCustomerModel ycmCustomerModel = instantiateModel(entity);
        ycmCustomerModel.setNick(entity.getNick());
        ycmCustomerModel.setName(entity.getName());
        ycmCustomerModel.setLastName(entity.getLastName());
        ycmCustomerModel.setEmail(entity.getEmail());
        Address address = entity.getAddress();
        ycmCustomerModel.setAddress(entity.getAddress());
        return ycmCustomerModel;
    }
}
